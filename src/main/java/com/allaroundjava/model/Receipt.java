package com.allaroundjava.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "RECEIPT")
@org.hibernate.annotations.Immutable
public final class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECEIPT_ID")
    @org.hibernate.annotations.Immutable
    private Set<LineItem> lineItems;

    Receipt() {

    }

    Receipt(LocalDateTime transactionDate, Set<LineItem> lineItems) {
        this.transactionDate = transactionDate;
        this.lineItems = lineItems;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public static Receipt newInstance(LocalDateTime transactionDate, Set<LineItem> transactions) {
        return new Receipt(transactionDate, Collections.unmodifiableSet(transactions));
    }
}
