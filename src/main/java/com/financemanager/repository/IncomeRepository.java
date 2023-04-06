package com.financemanager.repository;

import com.financemanager.DbConnection;
import com.financemanager.entity.Income;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncomeRepository {

    private final EntityManager entityManager;

    public IncomeRepository() {
        this.entityManager = DbConnection.getEntityManager();
    }

    public void insert(Income income) {
        entityManager.getTransaction().begin();
        entityManager.persist(income);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Income findById(String id) {
        return entityManager.find(Income.class, id);
    }

    public Set<Income> findAll() {
        List<Income> incomeList = entityManager.createQuery("select a from Income a", Income.class).getResultList();
        entityManager.close();
        return new HashSet<>(incomeList);
    }

    public void delete(Income income) {
        entityManager.getTransaction().begin();
        entityManager.remove(income);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
