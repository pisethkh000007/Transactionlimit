package com.WingBank.Transactionlimit.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accountlimits")
@Data
public class AccountLimits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long limitId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;

    @Column(name = "daily_limit", nullable = false)
    private BigDecimal dailyLimit;

    @Column(name = "transaction_limit", nullable = false)
    private BigDecimal transactionLimit;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency = "USD";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt ;

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
