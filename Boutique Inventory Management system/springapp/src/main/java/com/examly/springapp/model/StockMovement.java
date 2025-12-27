package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public StockMovement() {}
}

