package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stockmovement")
public class StockMovementController {

    @GetMapping("/{id}")
    public String getStockMovement(@PathVariable Long id){
        return "ok";
    }
}

