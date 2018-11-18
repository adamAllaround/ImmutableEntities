package com.allaroundjava.dao;

import com.allaroundjava.model.FinancialTransaction;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.Instant;

public class FinancialTransactionDaoImplTest {
    private static EntityManagerFactory entityManagerFactory;
    private static Dao<FinancialTransaction> finTransactionDao;

    @BeforeClass
    public static void beforeClass() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("immutableEntities");
        finTransactionDao = new FinancialTransactionDaoImpl(entityManagerFactory);
    }

    @Test
    public void whenPersistingEntity_thenNoException() {
        FinancialTransaction transaction =
                FinancialTransaction.newInstance(BigDecimal.valueOf(120), Instant.now());
        finTransactionDao.persist(transaction);
    }
}