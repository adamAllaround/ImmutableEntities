package com.allaroundjava.dao;

import com.allaroundjava.model.LineItem;
import com.allaroundjava.model.Receipt;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ReceiptDaoImplTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
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

        Receipt receipt = Receipt.newInstance(LocalDateTime.now(), lineItems);
        receiptDao.persist(receipt);
    }

    @Test
    public void havingImmutableRecept_whenModifyingLineItems_thenNoUpdate() {
        Optional<Receipt> receiptOptional = receiptDao.getById(1L);
        Receipt receipt = receiptOptional.get();
        int lineItemcount = receipt.getLineItems().size();

        LineItem item = LineItem.getBuilder()
                .withItemName("ketchup")
                .withPrice(BigDecimal.TEN)
                .withQuantity(2)
                .build();

        exception.expect(javax.persistence.RollbackException.class);
        receipt.getLineItems().add(item);
        receiptDao.merge(receipt);

        Receipt retrievedReceipt = receiptDao.getById(1L).get();
        Assert.assertEquals(lineItemcount, retrievedReceipt.getLineItems().size());
    }

}