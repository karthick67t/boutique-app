package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private String name;
    private String location;

    public Warehouse(){}

    public Long getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Long id) { this.warehouseId = id; }

    public String getName() { return name; }
    public void setName(String n) { this.name = n; }

    public String getLocation() { return location; }
    public void setLocation(String l) { this.location = l; }
}

