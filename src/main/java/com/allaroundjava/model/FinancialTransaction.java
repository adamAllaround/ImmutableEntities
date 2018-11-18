package com.allaroundjava.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "FINANCIAL_TRANSACTION")
@org.hibernate.annotations.Immutable
public final class FinancialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private Instant timestamp;

    private FinancialTransaction() {
    }

    private FinancialTransaction(BigDecimal amount, Instant timestamp) {
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

    public static FinancialTransaction newInstance(BigDecimal amount, Instant timestamp) {
        return new FinancialTransaction(amount, timestamp);
    }
}
