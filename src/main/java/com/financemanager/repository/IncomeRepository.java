package com.financemanager.repository;

import com.financemanager.DbConnection;
import com.financemanager.entity.Income;
import jakarta.persistence.EntityManager;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        EntityManager entityManager = DbConnection.getEntityManager();
        List<Income> incomeList = entityManager.createQuery("select a from Income a", Income.class).getResultList();
        entityManager.close();
        return new HashSet<>(incomeList);
    }

    public void deleteById(String id) {
        EntityManager entityManager = DbConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Optional<Income> income = Optional.ofNullable(entityManager.find(Income.class, id));
        income.ifPresent(entityManager::remove);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Long actualIncomesBalance() {
        Long incomesCount = entityManager
                .createQuery("select sum(e.amount) from Income e", Long.class)
                .getSingleResult();
        return incomesCount;
    }
}
