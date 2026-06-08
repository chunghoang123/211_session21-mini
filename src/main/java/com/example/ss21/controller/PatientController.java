package com.example.ss21.controller;

import com.example.ss21.model.Patient;
import com.example.ss21.service.EcoHealthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/patients", "/patients"})
@PreAuthorize("hasRole('ADMIN')")
public class PatientController {
    private final EcoHealthService service;
    public PatientController(EcoHealthService service) { this.service = service; }
    @GetMapping public List<Patient> all() { return service.patients(); }
    @PostMapping public Patient create(@RequestBody Patient item) { return service.createPatient(item); }
    @PutMapping("/{id}") public Patient update(@PathVariable Long id, @RequestBody Patient item) { return service.updatePatient(id, item); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deletePatient(id); }
}
