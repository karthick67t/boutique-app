package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;

import com.examly.springapp.model.Category;
import com.examly.springapp.repository.CategoryRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepo repo;

    public CategoryController(CategoryRepo repo){
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody(required = false) Category c){
        if(c == null){
            return ResponseEntity.badRequest().build();
        }
        Category saved = repo.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Category> list = repo.findAll();

        if(list == null || list.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){
        Category c = repo.findById(id).orElse(null);
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id,
                                           @RequestBody Category c){

        Category existing = repo.findById(id).orElse(null);

        if(existing != null){
            existing.setCategoryName(c.getCategoryName());
            repo.save(existing);
        }

        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<?> getPaginated(
            @PathVariable int page,
            @PathVariable int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("categoryId"));
        Page<Category> result = repo.findAll(pageable);

        return ResponseEntity.ok(result);
    }
}

