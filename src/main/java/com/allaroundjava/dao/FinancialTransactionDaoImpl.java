package com.allaroundjava.dao;

import com.allaroundjava.model.FinancialTransaction;

import javax.persistence.EntityManagerFactory;

class FinancialTransactionDaoImpl extends BaseDao<FinancialTransaction> {

    FinancialTransactionDaoImpl(EntityManagerFactory emf) {
        super(FinancialTransaction.class, emf);
    }
}
