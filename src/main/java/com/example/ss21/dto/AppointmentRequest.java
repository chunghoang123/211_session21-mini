package com.example.ss21.dto;

import java.time.LocalDateTime;

public record AppointmentRequest(Long doctorId, LocalDateTime appointmentTime) {}
