package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;

import com.examly.springapp.model.Warehouse;
import com.examly.springapp.repository.WarehouseRepo;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseRepo repo;

    public WarehouseController(WarehouseRepo repo){
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Warehouse> add(@RequestBody Warehouse w){
        Warehouse saved = repo.save(w);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getById(@PathVariable Long id){
        return ResponseEntity.ok(repo.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> update(@PathVariable Long id,
                                            @RequestBody Warehouse w){

        Warehouse existing = repo.findById(id).orElse(null);

        if(existing != null){
            existing.setName(w.getName());
            existing.setLocation(w.getLocation());
            repo.save(existing);
        }

        return ResponseEntity.ok(existing);
    }
    @GetMapping("/location/{location}")
public ResponseEntity<?> getByLocation(@PathVariable String location){

    List<Warehouse> list = repo.findByLocation(location);

    if(list == null || list.isEmpty()){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("No warehouses found at location: " + location);
    }

    return ResponseEntity.ok(list);
}

@GetMapping("/location/{location}/name/{name}")
public ResponseEntity<?> getByLocationAndName(
        @PathVariable String location,
        @PathVariable String name){

    List<Warehouse> list = repo.findByLocationAndName(location, name);

    return ResponseEntity.ok(list);
}


}

