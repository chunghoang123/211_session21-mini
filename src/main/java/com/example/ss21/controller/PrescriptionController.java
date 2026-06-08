package com.example.ss21.controller;

import com.example.ss21.dto.PrescriptionRequest;
import com.example.ss21.model.Prescription;
import com.example.ss21.service.EcoHealthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/prescriptions", "/prescriptions"})
public class PrescriptionController {
    private final EcoHealthService service;
    public PrescriptionController(EcoHealthService service) { this.service = service; }
    @PostMapping @PreAuthorize("hasRole('DOCTOR')") public Prescription create(@RequestBody PrescriptionRequest request) { return service.createPrescription(request); }
    @GetMapping("/my") @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','PATIENT')") public List<Prescription> my() { return service.myPrescriptions(); }
}
