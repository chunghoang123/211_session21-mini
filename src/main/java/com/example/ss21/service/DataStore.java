package com.example.ss21.service;

import com.example.ss21.model.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataStore {
    public final Map<Long, User> users = new ConcurrentHashMap<>();
    public final Map<Long, Doctor> doctors = new ConcurrentHashMap<>();
    public final Map<Long, Patient> patients = new ConcurrentHashMap<>();
    public final Map<Long, Medicine> medicines = new ConcurrentHashMap<>();
    public final Map<Long, Appointment> appointments = new ConcurrentHashMap<>();
    public final Map<Long, Prescription> prescriptions = new ConcurrentHashMap<>();
    public final Map<String, RefreshToken> refreshTokens = new ConcurrentHashMap<>();
    public final Map<String, Long> revokedAccessTokens = new ConcurrentHashMap<>();

    private final AtomicLong userSeq = new AtomicLong();
    private final AtomicLong doctorSeq = new AtomicLong();
    private final AtomicLong patientSeq = new AtomicLong();
    private final AtomicLong medicineSeq = new AtomicLong();
    private final AtomicLong appointmentSeq = new AtomicLong();
    private final AtomicLong prescriptionSeq = new AtomicLong();
    private final AtomicLong prescriptionDetailSeq = new AtomicLong();
    private final AtomicLong refreshSeq = new AtomicLong();

    public DataStore(PasswordEncoder encoder) {
        Doctor doctor = saveDoctor(new Doctor(null, "Dr. Nguyen Van A", "Cardiology", "0901000001", "doctor@eco.test"));
        Patient patient = savePatient(new Patient(null, "Tran Thi B", LocalDate.of(1998, 1, 20), "FEMALE", "0902000002", "Ha Noi"));
        saveMedicine(new Medicine(null, "Paracetamol", BigDecimal.valueOf(25000), "box", "Pain reliever"));
        saveUser(new User(null, "admin", encoder.encode("admin123"), Role.ROLE_ADMIN, AccountStatus.ACTIVE, LocalDateTime.now(), null, null));
        saveUser(new User(null, "doctor", encoder.encode("doctor123"), Role.ROLE_DOCTOR, AccountStatus.ACTIVE, LocalDateTime.now(), doctor.getId(), null));
        saveUser(new User(null, "patient", encoder.encode("patient123"), Role.ROLE_PATIENT, AccountStatus.ACTIVE, LocalDateTime.now(), null, patient.getId()));
    }

    public User saveUser(User user) { if (user.getId() == null) user.setId(userSeq.incrementAndGet()); users.put(user.getId(), user); return user; }
    public Doctor saveDoctor(Doctor doctor) { if (doctor.getId() == null) doctor.setId(doctorSeq.incrementAndGet()); doctors.put(doctor.getId(), doctor); return doctor; }
    public Patient savePatient(Patient patient) { if (patient.getId() == null) patient.setId(patientSeq.incrementAndGet()); patients.put(patient.getId(), patient); return patient; }
    public Medicine saveMedicine(Medicine medicine) { if (medicine.getId() == null) medicine.setId(medicineSeq.incrementAndGet()); medicines.put(medicine.getId(), medicine); return medicine; }
    public Appointment saveAppointment(Appointment appointment) { if (appointment.getId() == null) appointment.setId(appointmentSeq.incrementAndGet()); appointments.put(appointment.getId(), appointment); return appointment; }
    public Prescription savePrescription(Prescription prescription) { if (prescription.getId() == null) prescription.setId(prescriptionSeq.incrementAndGet()); prescription.getDetails().forEach(d -> { if (d.getId() == null) d.setId(prescriptionDetailSeq.incrementAndGet()); d.setPrescriptionId(prescription.getId()); }); prescriptions.put(prescription.getId(), prescription); return prescription; }
    public RefreshToken saveRefreshToken(RefreshToken token) { if (token.getId() == null) token.setId(refreshSeq.incrementAndGet()); refreshTokens.put(token.getToken(), token); return token; }
}
