package com.WingBank.Transactionlimit.dto;

import com.WingBank.Transactionlimit.model.AccountType;
import com.WingBank.Transactionlimit.model.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long account_Id;
    private Long customer_Id; // Include customer ID if needed
    private String account_Number;
    private AccountType account_Type;
    private BigDecimal balance;
    private AccountStatus account_Status;
}
