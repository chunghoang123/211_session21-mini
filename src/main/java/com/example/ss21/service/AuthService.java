package com.example.ss21.service;

import com.example.ss21.dto.*;
import com.example.ss21.exception.BadCredentialsException;
import com.example.ss21.exception.UnauthorizedException;
import com.example.ss21.model.*;
import com.example.ss21.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final DataStore store;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(DataStore store, PasswordEncoder encoder, JwtService jwtService) {
        this.store = store; this.encoder = encoder; this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest request) {
        User user = store.users.values().stream().filter(u -> u.getUsername().equals(request.username())).findFirst()
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (user.getStatus() == AccountStatus.LOCKED || !encoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        String access = jwtService.generateAccessToken(user.getId(), user.getUsername(), user.getRole());
        String refresh = UUID.randomUUID().toString();
        store.saveRefreshToken(new RefreshToken(null, user.getId(), refresh, LocalDateTime.now().plusDays(7), false));
        log.info("Login success for user {}", user.getUsername());
        return new TokenResponse(access, refresh);
    }

    public TokenResponse refresh(RefreshRequest request) {
        RefreshToken token = store.refreshTokens.get(request.refreshToken());
        if (token == null || token.isRevoked() || token.getExpiredAt().isBefore(LocalDateTime.now())) throw new UnauthorizedException("Refresh token invalid");
        User user = store.users.get(token.getUserId());
        if (user == null || user.getStatus() == AccountStatus.LOCKED) throw new UnauthorizedException("User locked");
        token.setRevoked(true);
        String access = jwtService.generateAccessToken(user.getId(), user.getUsername(), user.getRole());
        String refresh = UUID.randomUUID().toString();
        store.saveRefreshToken(new RefreshToken(null, user.getId(), refresh, LocalDateTime.now().plusDays(7), false));
        log.info("Refresh token success for user {}", user.getUsername());
        return new TokenResponse(access, refresh);
    }

    public void logout(String accessToken, LogoutRequest request) {
        if (accessToken != null) store.revokedAccessTokens.put(accessToken, System.currentTimeMillis());
        RefreshToken refresh = store.refreshTokens.get(request.refreshToken());
        if (refresh != null) refresh.setRevoked(true);
        log.info("Logout success");
    }

    public void revokeAllUserTokens(Long userId) {
        store.refreshTokens.values().stream().filter(t -> t.getUserId().equals(userId)).forEach(t -> t.setRevoked(true));
        log.info("Revoke token success for user {}", userId);
    }

    public boolean isAccessTokenRevoked(String token) { return store.revokedAccessTokens.containsKey(token); }
    public List<User> users() { return List.copyOf(store.users.values()); }
}
