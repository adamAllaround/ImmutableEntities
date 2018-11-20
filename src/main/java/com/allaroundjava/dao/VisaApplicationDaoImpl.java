package com.allaroundjava.dao;

import com.allaroundjava.model.VisaApplication;

import javax.persistence.EntityManagerFactory;

class VisaApplicationDaoImpl extends BaseDao<VisaApplication> {
    VisaApplicationDaoImpl(EntityManagerFactory emf) {
        super(VisaApplication.class, emf);
    }
}
