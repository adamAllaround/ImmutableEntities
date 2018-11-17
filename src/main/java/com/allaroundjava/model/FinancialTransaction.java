package com.allaroundjava.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public final class FinancialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private Instant timestamp;

    FinancialTransaction() {
    }

    FinancialTransaction(Long id, BigDecimal amount, Instant timestamp) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public static FinancialTransaction newInstance(Long id, BigDecimal amount, Instant timestamp) {
        return new FinancialTransaction(id, amount, timestamp);
    }
}
