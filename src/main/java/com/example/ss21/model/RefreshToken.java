package com.example.ss21.model;

import java.time.LocalDateTime;

public class RefreshToken {
    private Long id;
    private Long userId;
    private String token;
    private LocalDateTime expiredAt;
    private boolean revoked;

    public RefreshToken() {}
    public RefreshToken(Long id, Long userId, String token, LocalDateTime expiredAt, boolean revoked) {
        this.id = id; this.userId = userId; this.token = token; this.expiredAt = expiredAt; this.revoked = revoked;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
    public void setExpiredAt(LocalDateTime expiredAt) { this.expiredAt = expiredAt; }
    public boolean isRevoked() { return revoked; }
    public void setRevoked(boolean revoked) { this.revoked = revoked; }
}
