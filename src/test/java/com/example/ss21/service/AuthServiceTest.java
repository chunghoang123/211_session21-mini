package com.example.ss21.service;

import com.example.ss21.dto.LoginRequest;
import com.example.ss21.dto.RefreshRequest;
import com.example.ss21.exception.BadCredentialsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    AuthService authService;

    @Test
    void loginReturnsAccessAndRefreshToken() {
        var response = authService.login(new LoginRequest("admin", "admin123"));
        assertNotNull(response.accessToken());
        assertNotNull(response.refreshToken());
    }

    @Test
    void invalidPasswordReturnsUnauthorizedException() {
        assertThrows(BadCredentialsException.class, () -> authService.login(new LoginRequest("admin", "wrong")));
    }

    @Test
    void refreshRevokesOldRefreshTokenAndReturnsNewPair() {
        var login = authService.login(new LoginRequest("patient", "patient123"));
        var refreshed = authService.refresh(new RefreshRequest(login.refreshToken()));
        assertNotEquals(login.refreshToken(), refreshed.refreshToken());
        assertThrows(RuntimeException.class, () -> authService.refresh(new RefreshRequest(login.refreshToken())));
    }
}
