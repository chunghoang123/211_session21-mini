package com.example.ss21.model;

public class PrescriptionDetail {
    private Long id;
    private Long prescriptionId;
    private Long medicineId;
    private Integer quantity;
    private String instruction;

    public PrescriptionDetail() {}
    public PrescriptionDetail(Long id, Long prescriptionId, Long medicineId, Integer quantity, String instruction) {
        this.id = id; this.prescriptionId = prescriptionId; this.medicineId = medicineId; this.quantity = quantity; this.instruction = instruction;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(Long prescriptionId) { this.prescriptionId = prescriptionId; }
    public Long getMedicineId() { return medicineId; }
    public void setMedicineId(Long medicineId) { this.medicineId = medicineId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getInstruction() { return instruction; }
    public void setInstruction(String instruction) { this.instruction = instruction; }
}
