package com.example.ss21.controller;

import com.example.ss21.model.Doctor;
import com.example.ss21.service.EcoHealthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/doctors", "/doctors"})
public class DoctorController {
    private final EcoHealthService service;
    public DoctorController(EcoHealthService service) { this.service = service; }
    @GetMapping @PreAuthorize("hasAnyRole('ADMIN','PATIENT')") public List<Doctor> all() { return service.doctors(); }
    @PostMapping @PreAuthorize("hasRole('ADMIN')") public Doctor create(@RequestBody Doctor item) { return service.createDoctor(item); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public Doctor update(@PathVariable Long id, @RequestBody Doctor item) { return service.updateDoctor(id, item); }
    @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public void delete(@PathVariable Long id) { service.deleteDoctor(id); }
}
