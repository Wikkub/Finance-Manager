package com.financemanager.service;

import com.financemanager.entity.Category;
import com.financemanager.repository.CategoryRepository;

public class CategoryServices {
    private final CategoryRepository categoryRepository;

    public CategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory (String name) {
        Category category = new Category(name);
    }

}
