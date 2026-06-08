package com.example.ss21.security;

import com.example.ss21.model.Role;

public record JwtPrincipal(Long userId, String username, Role role, long expiresAt) {}
