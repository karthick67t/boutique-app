package com.examly.springapp.service;

import com.examly.springapp.model.Category;
import java.util.List;

public interface CategoryService {
    Category addCategory(Category c);
    List<Category> getAll();
    Category getById(Long id);
    Category update(Long id, Category c);
}

