package com.financemanager.repository;

import com.financemanager.entity.Category;
import com.financemanager.DbConnection;
import jakarta.persistence.EntityManager;

public class CategoryRepository {

    public void insert(Category category) {
        EntityManager entityManager = DbConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
