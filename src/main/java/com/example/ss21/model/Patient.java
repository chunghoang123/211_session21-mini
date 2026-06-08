package com.example.ss21.model;

import java.time.LocalDate;

public class Patient {
    private Long id;
    private String fullName;
    private LocalDate dob;
    private String gender;
    private String phone;
    private String address;

    public Patient() {}
    public Patient(Long id, String fullName, LocalDate dob, String gender, String phone, String address) {
        this.id = id; this.fullName = fullName; this.dob = dob; this.gender = gender; this.phone = phone; this.address = address;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
