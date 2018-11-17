package com.allaroundjava.dao;

import com.allaroundjava.model.FinancialTransaction;

import java.util.Optional;

public class FinancialTransactionDaoImpl implements FinancialTransactionDao {
    @Override
    public Optional<FinancialTransaction> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Long persist(FinancialTransaction c) {
        return null;
    }
}
