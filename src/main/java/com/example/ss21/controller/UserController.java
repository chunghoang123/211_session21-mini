package com.example.ss21.controller;

import com.example.ss21.model.*;
import com.example.ss21.service.AuthService;
import com.example.ss21.service.EcoHealthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/users", "/users"})
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final EcoHealthService service;
    private final AuthService authService;
    public UserController(EcoHealthService service, AuthService authService) { this.service = service; this.authService = authService; }
    @GetMapping public List<User> all() { return authService.users(); }
    @PostMapping public User create(@RequestBody User user) { return service.createUser(user); }
    @PutMapping("/{id}") public User update(@PathVariable Long id, @RequestBody User user) { return service.updateUser(id, user); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteUser(id); }
    @PostMapping("/{id}/lock") public User lock(@PathVariable Long id) { return service.lockUser(id); }
}
