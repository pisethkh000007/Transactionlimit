package com.WingBank.Transactionlimit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionTypeDTO {

    private Long transactionTypeId;
    private String transactionName;
    private String description;
    private LocalDateTime createdAt;
}
