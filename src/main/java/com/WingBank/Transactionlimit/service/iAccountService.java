package com.WingBank.Transactionlimit.service;

import com.WingBank.Transactionlimit.dto.AccountDTO;
import java.util.List;

public interface iAccountService {
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccountById(Long accountId);
    List<AccountDTO> getAllAccounts();
    AccountDTO updateAccount(Long accountId, AccountDTO accountDTO);
    void deleteAccount(Long accountId);
}
