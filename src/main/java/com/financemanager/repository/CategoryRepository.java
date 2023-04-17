package com.financemanager.repository;

import com.financemanager.entity.Category;
import com.financemanager.DbConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    public Category findByName(String name) {
        EntityManager entityManager = DbConnection.getEntityManager();
        TypedQuery <Category> query = entityManager.createQuery("FROM Category c where c.name = :name", Category.class);
        query.setParameter("name", name);
        List<Category> result = query.getResultList();
        entityManager.close();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public Set<Category> findAll() {
        EntityManager entityManager = DbConnection.getEntityManager();
        List<Category> categories = entityManager.createQuery("select a from Category a", Category.class).getResultList();
        entityManager.close();
        return new HashSet<>(categories);
    }

    public void deleteById(String id) {
        EntityManager entityManager = DbConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Optional<Category> category = Optional.ofNullable(entityManager.find(Category.class, id));
        category.ifPresent(entityManager::remove);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
