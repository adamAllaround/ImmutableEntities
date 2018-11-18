package com.allaroundjava.dao;

import com.allaroundjava.model.LineItem;
import com.allaroundjava.model.Receipt;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ReceiptDaoImplTest {
    private static EntityManagerFactory entityManagerFactory;
    private static Dao<Receipt> receiptDao;

    @BeforeClass
    public static void beforeClass() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("immutableEntities");
        receiptDao = new ReceiptDaoImpl(entityManagerFactory);
    }

    @Test
    public void whenPersistingEntity_thenNoException() {
        LineItem item = LineItem.getBuilder()
                .withItemName("eggs")
                .withPrice(BigDecimal.TEN)
                .withQuantity(2)
                .build();

        Set<LineItem> lineItems = new HashSet<>();
        lineItems.add(item);

        Receipt receipt = Receipt.valueOf(LocalDateTime.now(), lineItems);
        receiptDao.persist(receipt);

    }

}