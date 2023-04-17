package com.financemanager.service;

import com.financemanager.entity.Category;
import com.financemanager.repository.CategoryRepository;

import java.util.Set;

public class CategoryServices {
    private final CategoryRepository categoryRepository;

    public CategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory (String name) {
        Category existingCategory = categoryRepository.findByName(name);
        if (existingCategory != null) {
            System.out.println(name + " already exists");
            return;
        }
        Category category = new Category(name);
        categoryRepository.insert(category);
        System.out.println("Category: " + name + " added!");

//        String findName = String.valueOf(categoryRepository.findByName(name));
//        if (!(findName.equals(name))) {
//            Category category = new Category(name);
//            categoryRepository.insert(category);
//            System.out.println("Category: " + name + " added!");
//        } else {
//            System.out.println(name + " already exists");
//        }
    }

    public void deleteCategory (String id) {
        categoryRepository.deleteById(id);
        System.out.println("Category deleted!");
    }

    public Set<Category> findAll() {
        return categoryRepository.findAll();
    }


}
