package com.allaroundjava.dao;

import com.allaroundjava.model.Passport;
import com.allaroundjava.model.Person;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonPassportTest {
    private static EntityManagerFactory entityManagerFactory;
    private static Dao<Person> personDao;
    private static Dao<Passport> passportDao;

    @BeforeClass
    public static void beforeClass() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("immutableEntities");
        personDao = new PersonDaoImpl(entityManagerFactory);
        passportDao = new PassportDaoImpl(entityManagerFactory);
    }

    @Test
    public void havingImmutablePassport_whenPersonModified_thenPassportPersonModified() {
        Person person = new Person();
        person.setName("Mary");
        personDao.persist(person);

        Passport passport = Passport.newInstance("ABC1234", person);
        passportDao.persist(passport);

        person.setName("John");
        personDao.merge(person);

        Assert.assertEquals("John", passport.getPerson().getName());
    }

}