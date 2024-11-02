package com.WingBank.Transactionlimit.service;

import com.WingBank.Transactionlimit.dto.AccountLimitsDTO;
import java.util.List;

public interface IAccountLimits {
    AccountLimitsDTO createAccountLimits(AccountLimitsDTO accountLimitsDTO);
    AccountLimitsDTO getAccountLimitsById(Long limitId);
    List<AccountLimitsDTO> getAllAccountLimits();
    AccountLimitsDTO updateAccountLimits(Long limitId, AccountLimitsDTO accountLimitsDTO);
    void deleteAccountLimits(Long limitId);
}
