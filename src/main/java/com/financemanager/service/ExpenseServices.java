package com.financemanager.service;

import com.financemanager.entity.Expense;
import com.financemanager.repository.ExpenseRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ExpenseServices {
    private final ExpenseRepository expenseRepository;

    public ExpenseServices(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense (Expense expense) throws SQLException {
        expenseRepository.insert(expense);
    }

    public void deleteExpenseById (String id) {
        expenseRepository.deleteById(id);
        System.out.println("Expense deleted!");
    }

    public Set<Expense> findAllExpenses () {
        return expenseRepository.findAll();
    }

    public List findExpensesByCategoryId(String categoryId) {
        return expenseRepository.findExpensesByCategory(categoryId);
    }
}
