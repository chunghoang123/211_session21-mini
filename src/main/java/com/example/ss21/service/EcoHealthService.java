package com.example.ss21.service;

import com.example.ss21.dto.AppointmentRequest;
import com.example.ss21.dto.PrescriptionRequest;
import com.example.ss21.exception.NotFoundException;
import com.example.ss21.model.*;
import com.example.ss21.security.CurrentUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EcoHealthService {
    private final DataStore store;
    private final AuthService authService;

    public EcoHealthService(DataStore store, AuthService authService) { this.store = store; this.authService = authService; }

    public List<Doctor> doctors() { return List.copyOf(store.doctors.values()); }
    public Doctor createDoctor(Doctor doctor) { return store.saveDoctor(doctor); }
    public Doctor updateDoctor(Long id, Doctor doctor) { requireDoctor(id); doctor.setId(id); return store.saveDoctor(doctor); }
    public void deleteDoctor(Long id) { requireDoctor(id); store.doctors.remove(id); }

    public List<Patient> patients() { return List.copyOf(store.patients.values()); }
    public Patient createPatient(Patient patient) { return store.savePatient(patient); }
    public Patient updatePatient(Long id, Patient patient) { requirePatient(id); patient.setId(id); return store.savePatient(patient); }
    public void deletePatient(Long id) { requirePatient(id); store.patients.remove(id); }

    public List<Medicine> medicines() { return List.copyOf(store.medicines.values()); }
    public Medicine createMedicine(Medicine medicine) { return store.saveMedicine(medicine); }
    public Medicine updateMedicine(Long id, Medicine medicine) { requireMedicine(id); medicine.setId(id); return store.saveMedicine(medicine); }
    public void deleteMedicine(Long id) { requireMedicine(id); store.medicines.remove(id); }

    public User createUser(User user) { user.setStatus(AccountStatus.ACTIVE); user.setCreatedAt(LocalDateTime.now()); return store.saveUser(user); }
    public User updateUser(Long id, User user) { requireUser(id); user.setId(id); return store.saveUser(user); }
    public void deleteUser(Long id) { requireUser(id); store.users.remove(id); authService.revokeAllUserTokens(id); }
    public User lockUser(Long id) { User user = requireUser(id); user.setStatus(AccountStatus.LOCKED); authService.revokeAllUserTokens(id); return user; }

    public Appointment bookAppointment(AppointmentRequest request) {
        User user = CurrentUser.get();
        requireDoctor(request.doctorId());
        if (user.getPatientId() == null) throw new NotFoundException("Patient profile not found");
        return store.saveAppointment(new Appointment(null, user.getPatientId(), request.doctorId(), request.appointmentTime(), AppointmentStatus.BOOKED));
    }

    public List<Appointment> myAppointments() {
        User user = CurrentUser.get();
        if (user.getRole() == Role.ROLE_DOCTOR) return store.appointments.values().stream().filter(a -> a.getDoctorId().equals(user.getDoctorId())).toList();
        if (user.getRole() == Role.ROLE_PATIENT) return store.appointments.values().stream().filter(a -> a.getPatientId().equals(user.getPatientId())).toList();
        return List.copyOf(store.appointments.values());
    }

    public Prescription createPrescription(PrescriptionRequest request) {
        User user = CurrentUser.get();
        Appointment appointment = requireAppointment(request.appointmentId());
        if (!appointment.getDoctorId().equals(user.getDoctorId())) throw new NotFoundException("Appointment not found for current doctor");
        requirePatient(request.patientId());
        List<PrescriptionDetail> details = request.details().stream().map(item -> {
            requireMedicine(item.medicineId());
            return new PrescriptionDetail(null, null, item.medicineId(), item.quantity(), item.instruction());
        }).toList();
        return store.savePrescription(new Prescription(null, user.getDoctorId(), request.patientId(), request.appointmentId(), LocalDateTime.now(), details));
    }

    public List<Prescription> myPrescriptions() {
        User user = CurrentUser.get();
        if (user.getRole() == Role.ROLE_PATIENT) return store.prescriptions.values().stream().filter(p -> p.getPatientId().equals(user.getPatientId())).toList();
        if (user.getRole() == Role.ROLE_DOCTOR) return store.prescriptions.values().stream().filter(p -> p.getDoctorId().equals(user.getDoctorId())).toList();
        return List.copyOf(store.prescriptions.values());
    }

    private User requireUser(Long id) { User item = store.users.get(id); if (item == null) throw new NotFoundException("User not found"); return item; }
    private Doctor requireDoctor(Long id) { Doctor item = store.doctors.get(id); if (item == null) throw new NotFoundException("Doctor not found"); return item; }
    private Patient requirePatient(Long id) { Patient item = store.patients.get(id); if (item == null) throw new NotFoundException("Patient not found"); return item; }
    private Medicine requireMedicine(Long id) { Medicine item = store.medicines.get(id); if (item == null) throw new NotFoundException("Medicine not found"); return item; }
    private Appointment requireAppointment(Long id) { Appointment item = store.appointments.get(id); if (item == null) throw new NotFoundException("Appointment not found"); return item; }
}
