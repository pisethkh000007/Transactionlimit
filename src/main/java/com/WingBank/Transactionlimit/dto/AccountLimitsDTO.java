package com.WingBank.Transactionlimit.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountLimitsDTO {

    private Long limitId;
    private Long accountId;
    private Long transactionTypeId;
    private BigDecimal dailyLimit;
    private BigDecimal transactionLimit;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
