package com.allaroundjava.dao;

import com.allaroundjava.model.Passport;

import javax.persistence.EntityManagerFactory;

class PassportDaoImpl extends BaseDao<Passport> {
    PassportDaoImpl(EntityManagerFactory emf) {
        super(Passport.class, emf);
    }
}
