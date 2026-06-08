package com.example.ss21.model;

import java.time.LocalDateTime;

public class Appointment {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;

    public Appointment() {}
    public Appointment(Long id, Long patientId, Long doctorId, LocalDateTime appointmentTime, AppointmentStatus status) {
        this.id = id; this.patientId = patientId; this.doctorId = doctorId; this.appointmentTime = appointmentTime; this.status = status;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
}
