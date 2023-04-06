package com.financemanager.service;

import com.financemanager.entity.Income;
import com.financemanager.repository.IncomeRepository;

public class IncomeServices {
    private final IncomeRepository incomeRepository;

    public IncomeServices(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public void addIncome (Income income) {
        incomeRepository.insert(income);
    }

    public void deleteIncome (Income income){

    }
}
