package com.financemanager.repository;

import com.financemanager.entity.Category;
import com.financemanager.DbConnection;
import com.financemanager.entity.Income;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryRepository {

    public void insert(Category category) {
        EntityManager entityManager = DbConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Category findById(String id) {
        EntityManager entityManager = DbConnection.getEntityManager();
        Category category = entityManager.find(Category.class, id);
        entityManager.close();
        return category;
    }

    public Set<Category> findAll() {
        EntityManager entityManager = DbConnection.getEntityManager();
        List<Category> categories = entityManager.createQuery("select a from Category a", Category.class).getResultList();
        entityManager.close();
        return new HashSet<>(categories);
    }

    public void delete(Category category) {
        EntityManager entityManager = DbConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
