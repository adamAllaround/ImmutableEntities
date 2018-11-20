package com.allaroundjava.model;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Immutable
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String passportNumber;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    private Passport() {
    }

    private Passport(String passportNumber, Person person) {
        this.passportNumber = passportNumber;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Person getPerson() {
        return person;
    }

    public static Passport newInstance(String passportNumber, Person person) {
        return new Passport(passportNumber, person);
    }
}
