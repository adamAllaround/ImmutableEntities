package com.allaroundjava.dao;

import com.allaroundjava.model.Person;

import javax.persistence.EntityManagerFactory;

class PersonDaoImpl extends BaseDao<Person> {
    PersonDaoImpl(EntityManagerFactory emf) {
        super(Person.class, emf);
    }
}
