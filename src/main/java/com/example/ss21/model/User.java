package com.example.ss21.model;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private Long doctorId;
    private Long patientId;

    public User() {
    }

    public User(Long id, String username, String password, Role role, AccountStatus status, LocalDateTime createdAt, Long doctorId, Long patientId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
}
