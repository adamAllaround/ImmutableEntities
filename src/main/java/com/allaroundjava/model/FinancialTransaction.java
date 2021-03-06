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

    FinancialTransaction() {
    }

    FinancialTransaction(BigDecimal amount, Instant timestamp) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinancialTransaction)) return false;

        FinancialTransaction that = (FinancialTransaction) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!amount.equals(that.amount)) return false;
        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + amount.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
