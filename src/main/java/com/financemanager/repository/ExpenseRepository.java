package com.financemanager.repository;

import com.financemanager.DbConnection;
import com.financemanager.entity.Expense;
import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseRepository {
    private final EntityManager entityManager;
    private Connection connection;

    public ExpenseRepository () {
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

}
