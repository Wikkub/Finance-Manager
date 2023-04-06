package com.financemanager.service;

import com.financemanager.entity.Category;
import com.financemanager.repository.CategoryRepository;

public class CategoryServices {
    private final CategoryRepository categoryRepository;

    public CategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory (String name) {
        boolean ifCategoryExists = true;
        if (!ifCategoryExists) {
            Category category = new Category(name);
            System.out.println("Category: " + name + " added!");
        } else {
            System.out.println(name + " already exists");
        }
    }

    public void deleteCategory (String id) {
        Category category = categoryRepository.findById(id);
        categoryRepository.delete(category);
        System.out.println("Category deleted!");
    }

}
