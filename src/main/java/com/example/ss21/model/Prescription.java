package com.example.ss21.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private Long appointmentId;
    private LocalDateTime createdAt;
    private List<PrescriptionDetail> details = new ArrayList<>();

    public Prescription() {}
    public Prescription(Long id, Long doctorId, Long patientId, Long appointmentId, LocalDateTime createdAt, List<PrescriptionDetail> details) {
        this.id = id; this.doctorId = doctorId; this.patientId = patientId; this.appointmentId = appointmentId; this.createdAt = createdAt; this.details = details;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<PrescriptionDetail> getDetails() { return details; }
    public void setDetails(List<PrescriptionDetail> details) { this.details = details; }
}
