package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;

import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepo;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepo repo;

    public ProductController(ProductRepo repo){
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product p){
        Product saved = repo.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return ResponseEntity.ok(repo.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,
                                          @RequestBody Product p){

        Product existing = repo.findById(id).orElse(null);

        if(existing != null){
            existing.setProductName(p.getProductName());
            existing.setDescription(p.getDescription());
            existing.setCategory(p.getCategory());
            repo.save(existing);
        }

        return ResponseEntity.ok(existing);
    }
    @GetMapping("/category/{categoryName}")
public ResponseEntity<List<Product>> getByCategory(@PathVariable String categoryName){
    return ResponseEntity.ok(repo.findByCategoryName(categoryName));
}

@GetMapping("/name/{name}")
public ResponseEntity<?> getByName(@PathVariable String name){

    List<Product> list = repo.findByProductName(name);

    if(list == null || list.isEmpty()){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No products found with name: " + name);
    }

    return ResponseEntity.ok(list);
}


}

