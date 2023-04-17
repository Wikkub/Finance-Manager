package com.financemanager.service;

import com.financemanager.entity.Expense;
import com.financemanager.repository.ExpenseRepository;

import java.sql.SQLException;

public class ExpenseServices {
    private final ExpenseRepository expenseRepository;

    public ExpenseServices(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense (Expense expense) throws SQLException {
        expenseRepository.insert(expense);
    }
}
