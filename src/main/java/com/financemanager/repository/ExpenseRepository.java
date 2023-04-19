package com.financemanager.repository;

import com.financemanager.DbConnection;
import com.financemanager.entity.Expense;
import com.financemanager.entity.Income;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ExpenseRepository {
    private final EntityManager entityManager;
    private Connection connection;

    public ExpenseRepository() {
        this.entityManager = DbConnection.getEntityManager();
        //this.connection = connection;
    }

    public void insert(Expense expense) throws SQLException {
        entityManager.getTransaction().begin();
        entityManager.persist(expense);
        entityManager.getTransaction().commit();
        entityManager.close();
//        PreparedStatement selectCategoryPS = connection.prepareStatement
//                ("SELECT * FROM category WHERE NAME =?");
//        selectCategoryPS.setString(1, expense.getCategory().getName());
//        ResultSet categoryRS = selectCategoryPS.executeQuery();
//        boolean categoryExists = categoryRS.next();
//
//        if(!categoryExists) {
//            PreparedStatement insertCategoryPS = connection.prepareStatement
//                    ("INSERT INTO category(id, name) VALUES (?, ?");
//            insertCategoryPS.setString(1, expense.getCategory().getId());
//            insertCategoryPS.setString(2, expense.getCategory().getName());
//
//            insertCategoryPS.execute();
//
//            PreparedStatement insertExpensePS = connection.prepareStatement(
//                    ("INSERT INTO expense(id, amount, category, dateAdded, comment)" +
//                            "VALUES (?, ?, ?, ?, ?)"));
//
//            insertExpensePS.setString(1, expense.getId());
//            insertExpensePS.setInt(2, expense.getAmount());
//            insertExpensePS.setString(3, expense.getCategory().getId());
//            insertExpensePS.setString(4, expense.getDateAdded().toString());
//            insertExpensePS.setString(5, expense.getComment());
//
//            System.out.println("Category and expense added!");
//        }
//
//            //category exists
//        PreparedStatement insertExpensePS = connection.prepareStatement
//                ("INSERT INTO expense(id, amount, category, dateAdded, comment)" +
//                        "VALUES (?, ?, ?, ?, ?)");
//        insertExpensePS.setString(1, expense.getId());
//        insertExpensePS.setInt(2, expense.getAmount());
//        insertExpensePS.setString(3, categoryRS.getString("id"));
//        insertExpensePS.setString(4, expense.getDateAdded().toString());
//        insertExpensePS.setString(5, expense.getComment());
//        System.out.println("Expense added!");
    }

    public Expense findExpenseById(String id) {
        return entityManager.find(Expense.class, id);
    }

    public void deleteById(String id) {
        EntityManager entityManager = DbConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Optional<Expense> expense = Optional.ofNullable(entityManager.find(Expense.class, id));
        expense.ifPresent(entityManager::remove);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Set<Expense> findAll() {
        EntityManager entityManager = DbConnection.getEntityManager();
        entityManager.getTransaction().begin();
        List<Expense> expenses = entityManager.createQuery("select a from Expense a", Expense.class).getResultList();
        entityManager.close();
        return new HashSet<>(expenses);
    }

    public Expense findById(String id) {
        return entityManager.find(Expense.class, id);
    }

    public List<Expense> findExpensesByCategory(String categoryId) {
        TypedQuery<Expense> query = entityManager.createQuery("from Expense where category.id = :category", Expense.class);
        query.setParameter("category", categoryId);
        return query.getResultList();
    }

    public Long actualExpensesBalance() {
        Long expensesCount = entityManager
                .createQuery("select sum(e.amount) from Expense e", Long.class)
                .getSingleResult();
        return expensesCount;
    }

    public List<Expense> findExpensesByDate (LocalDate date) {
        TypedQuery<Expense> query = entityManager.createQuery("from Expense where dateAdded = :dateAdded", Expense.class);
        query.setParameter("dateAdded", date);
        return query.getResultList();
    }

    public List<Expense> findExpensesBetweenDates (LocalDate date, LocalDate date2) {
        TypedQuery<Expense> query = entityManager.createQuery("from Expense where dateAdded BETWEEN :date1 AND :date2", Expense.class);
        query.setParameter("date1", date).setParameter("date2", date2);
        return query.getResultList();
    }


}
