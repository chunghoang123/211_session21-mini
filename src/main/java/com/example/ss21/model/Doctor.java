package com.example.ss21.model;

public class Doctor {
    private Long id;
    private String fullName;
    private String specialization;
    private String phone;
    private String email;

    public Doctor() {}
    public Doctor(Long id, String fullName, String specialization, String phone, String email) {
        this.id = id; this.fullName = fullName; this.specialization = specialization; this.phone = phone; this.email = email;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
