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

    @Column(nullable = false, name = "transaction_date")
    private LocalDateTime transactionDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id")
    private Set<LineItem> lineItems;

    private Receipt() {

    }

    private Receipt(LocalDateTime transactionDate, Set<LineItem> lineItems) {
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
        return Collections.unmodifiableSet(lineItems);
    }

    public static Receipt newInstance(LocalDateTime transactionDate, Set<LineItem> transactions) {
        return new Receipt(transactionDate, Collections.unmodifiableSet(transactions));
    }
}
