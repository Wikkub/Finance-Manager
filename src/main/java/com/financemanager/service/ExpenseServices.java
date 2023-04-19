package com.financemanager.service;

import com.financemanager.entity.Expense;
import com.financemanager.repository.ExpenseRepository;
import jakarta.persistence.TypedQuery;

import java.sql.SQLException;
import java.time.LocalDate;
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

    public List<Expense> findExpensesByDate (LocalDate date) {
        return expenseRepository.findExpensesByDate(date);
    }

    public List<Expense> findExpensesBetweenDates (LocalDate date1, LocalDate date2) {
        return expenseRepository.findExpensesBetweenDates(date1, date2);
    }
}
