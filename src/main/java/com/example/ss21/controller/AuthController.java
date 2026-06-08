package com.example.ss21.controller;

import com.example.ss21.dto.*;
import com.example.ss21.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/auth", "/auth"})
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) { return authService.login(request); }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshRequest request) { return authService.refresh(request); }

    @PostMapping("/logout")
    public void logout(HttpServletRequest servletRequest, @RequestBody LogoutRequest request) {
        String header = servletRequest.getHeader("Authorization");
        authService.logout(header != null && header.startsWith("Bearer ") ? header.substring(7) : null, request);
    }
}
