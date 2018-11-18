package com.allaroundjava.dao;

import com.allaroundjava.model.FinancialTransaction;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Test
    public void havingImmutableEntity_whenMerge_thenNoUpdate()
            throws NoSuchFieldException, IllegalAccessException {
        Optional<FinancialTransaction> transactionOptional = finTransactionDao.getById(1L);
        FinancialTransaction transaction = transactionOptional.get();
        Field amountField = transaction.getClass().getDeclaredField("amount");
        amountField.setAccessible(true);
        BigDecimal newAmount = BigDecimal.valueOf(1900);
        amountField.set(transaction, newAmount);
        finTransactionDao.merge(transaction);

        FinancialTransaction retrievedTransaction = finTransactionDao.getById(1L).get();
        Assert.assertNotEquals(newAmount, retrievedTransaction.getAmount());
    }

    @Test
    public void havingImmutableEntity_whenPersist_thenHashCodeChanges() {
        Set<FinancialTransaction> financialTransactions = new HashSet<>();
        Instant timestamp = Instant.now();
        FinancialTransaction one =
                FinancialTransaction.newInstance(BigDecimal.TEN, timestamp);
        financialTransactions.add(one);
        financialTransactions.add(one);
        Assert.assertEquals(1, financialTransactions.size());

        finTransactionDao.persist(one);
        financialTransactions.add(one);
        Assert.assertEquals(2, financialTransactions.size());
    }
}