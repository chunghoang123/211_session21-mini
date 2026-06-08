package com.example.ss21.security;

import com.example.ss21.exception.UnauthorizedException;
import com.example.ss21.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Component
public class JwtService {
    private final String secret;
    private final long accessTtlSeconds;

    public JwtService(@Value("${app.jwt.secret:EcoHealthSecretKeyMustBeLongEnoughForHmac}") String secret,
                      @Value("${app.jwt.access-ttl-seconds:1800}") long accessTtlSeconds) {
        this.secret = secret;
        this.accessTtlSeconds = accessTtlSeconds;
    }

    public String generateAccessToken(Long userId, String username, Role role) {
        long exp = Instant.now().plusSeconds(accessTtlSeconds).getEpochSecond();
        String payload = userId + ":" + username + ":" + role.name() + ":" + exp;
        return base64(payload) + "." + sign(payload);
    }

    public JwtPrincipal parse(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 2) throw new UnauthorizedException("Invalid token");
            String payload = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            if (!sign(payload).equals(parts[1])) throw new UnauthorizedException("Invalid token");
            String[] claims = payload.split(":");
            if (claims.length != 4) throw new UnauthorizedException("Invalid token");
            long exp = Long.parseLong(claims[3]);
            if (Instant.now().getEpochSecond() > exp) throw new UnauthorizedException("Token expired");
            return new JwtPrincipal(Long.parseLong(claims[0]), claims[1], Role.valueOf(claims[2]), exp);
        } catch (UnauthorizedException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UnauthorizedException("Invalid token");
        }
    }

    private String base64(String value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private String sign(String payload) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ex) {
            throw new IllegalStateException("Cannot sign JWT", ex);
        }
    }
}
