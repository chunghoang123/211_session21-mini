package com.example.ss21.controller;

import com.example.ss21.model.Medicine;
import com.example.ss21.service.EcoHealthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/medicines", "/medicines"})
public class MedicineController {
    private final EcoHealthService service;
    public MedicineController(EcoHealthService service) { this.service = service; }
    @GetMapping @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')") public List<Medicine> all() { return service.medicines(); }
    @PostMapping @PreAuthorize("hasRole('ADMIN')") public Medicine create(@RequestBody Medicine item) { return service.createMedicine(item); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public Medicine update(@PathVariable Long id, @RequestBody Medicine item) { return service.updateMedicine(id, item); }
    @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public void delete(@PathVariable Long id) { service.deleteMedicine(id); }
}
