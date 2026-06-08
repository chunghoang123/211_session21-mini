package com.example.ss21.model;

import java.math.BigDecimal;

public class Medicine {
    private Long id;
    private String name;
    private BigDecimal price;
    private String unit;
    private String description;

    public Medicine() {}
    public Medicine(Long id, String name, BigDecimal price, String unit, String description) {
        this.id = id; this.name = name; this.price = price; this.unit = unit; this.description = description;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
