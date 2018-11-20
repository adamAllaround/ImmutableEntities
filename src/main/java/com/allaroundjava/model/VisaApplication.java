package com.allaroundjava.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@org.hibernate.annotations.Immutable
public final class VisaApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_SUBMITTED")
    private LocalDate dateSubmitted;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    private VisaApplication() {
    }

    private VisaApplication(LocalDate dateSubmitted, Person person) {
        this.dateSubmitted = dateSubmitted;
        this.person = person;
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public Person getPerson() {
        return person;
    }

    public static VisaApplication newInstance(LocalDate dateSubmitted, Person person) {
        return new VisaApplication(dateSubmitted, person);
    }

}
