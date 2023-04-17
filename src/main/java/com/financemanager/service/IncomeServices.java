package com.financemanager.service;

import com.financemanager.entity.Income;
import com.financemanager.repository.IncomeRepository;

import java.util.Set;

public class IncomeServices {
    private final IncomeRepository incomeRepository;

    public IncomeServices(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public void addIncome(Income income) {
        incomeRepository.insert(income);
    }

    public void deleteIncomeById(String id) {
        Income income = incomeRepository.findById(id);
        incomeRepository.deleteById(id);
        System.out.println("Income deleted!");
    }

    public Set<Income> findAll() {
        return incomeRepository.findAll();
    }
}
