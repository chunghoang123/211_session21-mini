package com.example.ss21.dto;

import java.util.List;

public record PrescriptionRequest(Long patientId, Long appointmentId, List<PrescriptionItemRequest> details) {}
