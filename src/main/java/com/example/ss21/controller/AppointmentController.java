package com.example.ss21.controller;

import com.example.ss21.dto.AppointmentRequest;
import com.example.ss21.model.Appointment;
import com.example.ss21.service.EcoHealthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/appointments", "/appointments"})
public class AppointmentController {
    private final EcoHealthService service;
    public AppointmentController(EcoHealthService service) { this.service = service; }
    @PostMapping @PreAuthorize("hasRole('PATIENT')") public Appointment book(@RequestBody AppointmentRequest request) { return service.bookAppointment(request); }
    @GetMapping("/my") @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','PATIENT')") public List<Appointment> my() { return service.myAppointments(); }
}
