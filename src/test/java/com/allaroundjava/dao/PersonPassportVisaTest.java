package com.allaroundjava.dao;

import com.allaroundjava.model.Passport;
import com.allaroundjava.model.Person;
import com.allaroundjava.model.VisaApplication;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class PersonPassportVisaTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private static EntityManagerFactory entityManagerFactory;
    private static Dao<Person> personDao;
    private static Dao<Passport> passportDao;
    private static Dao<VisaApplication> visaApplicationDao;

    @BeforeClass
    public static void beforeClass() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("immutableEntities");
        personDao = new PersonDaoImpl(entityManagerFactory);
        passportDao = new PassportDaoImpl(entityManagerFactory);
        visaApplicationDao = new VisaApplicationDaoImpl(entityManagerFactory);
    }

    @Test
    public void havingImmutablePassport_whenGetPerson_thenNewPersonCreated() {
        Person person = new Person();
        person.setName("Mary");
        personDao.persist(person);

        Passport passport = Passport.newInstance("ABC1234", person);
        passportDao.persist(passport);

        VisaApplication visaApplication =
                VisaApplication.newInstance(LocalDate.now(), passport.getPerson());
        expectedException.expect(Exception.class);
        visaApplicationDao.persist(visaApplication);

        Assert.assertNotEquals(person.getId(), visaApplication.getPerson().getId());
    }

}