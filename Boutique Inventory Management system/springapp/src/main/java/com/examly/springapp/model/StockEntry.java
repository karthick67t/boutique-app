package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class StockEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public StockEntry() {}
}

