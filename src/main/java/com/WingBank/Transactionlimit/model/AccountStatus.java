package com.WingBank.Transactionlimit.model;

public enum AccountStatus {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
    private String accountStatus;

    AccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}