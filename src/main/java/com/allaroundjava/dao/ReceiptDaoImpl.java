package com.allaroundjava.dao;

import com.allaroundjava.model.Receipt;

import javax.persistence.EntityManagerFactory;

class ReceiptDaoImpl extends BaseDao<Receipt> {
    ReceiptDaoImpl(EntityManagerFactory emf) {
        super(Receipt.class, emf);
    }
}
