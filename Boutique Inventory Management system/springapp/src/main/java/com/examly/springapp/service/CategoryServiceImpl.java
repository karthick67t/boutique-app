package com.examly.springapp.service;

import java.util.*;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Category;
import com.examly.springapp.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo repo;

    public CategoryServiceImpl(CategoryRepo repo){
        this.repo = repo;
    }

    @Override
    public Category addCategory(Category c){
        return repo.save(c);
    }

    @Override
    public List<Category> getAll(){
        return repo.findAll();
    }

    @Override
    public Category getById(Long id){
        Optional<Category> ob = repo.findById(id);
        return ob.isPresent() ? ob.get() : null;
    }

    @Override
    public Category update(Long id, Category c){
        Optional<Category> ob = repo.findById(id);
        if(ob.isPresent()){
            Category ex = ob.get();
            ex.setCategoryName(c.getCategoryName());
            return repo.save(ex);
        }
        return null;
    }
}

