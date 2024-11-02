package com.WingBank.Transactionlimit.service;

import com.WingBank.Transactionlimit.dto.AccountLimitsDTO;
import com.WingBank.Transactionlimit.model.AccountLimits;
import com.WingBank.Transactionlimit.model.Account;
import com.WingBank.Transactionlimit.model.TransactionType;
import com.WingBank.Transactionlimit.repository.AccountLimitsRepository;
import com.WingBank.Transactionlimit.repository.AccountRepository;
import com.WingBank.Transactionlimit.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountLimitsService implements IAccountLimits {

    private final AccountLimitsRepository accountLimitsRepository;
    private final AccountRepository accountRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public AccountLimitsService(AccountLimitsRepository accountLimitsRepository,
                                AccountRepository accountRepository,
                                TransactionTypeRepository transactionTypeRepository) {
        this.accountLimitsRepository = accountLimitsRepository;
        this.accountRepository = accountRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public AccountLimitsDTO createAccountLimits(AccountLimitsDTO accountLimitsDTO) {
        Optional<Account> account = accountRepository.findById(accountLimitsDTO.getAccountId());
        Optional<TransactionType> transactionType = transactionTypeRepository.findById(accountLimitsDTO.getTransactionTypeId());

        if (account.isPresent() && transactionType.isPresent()) {
            AccountLimits accountLimits = new AccountLimits();
            accountLimits.setAccount(account.get());
            accountLimits.setTransactionType(transactionType.get());
            accountLimits.setDailyLimit(accountLimitsDTO.getDailyLimit());
            accountLimits.setTransactionLimit(accountLimitsDTO.getTransactionLimit());
            accountLimits.setCurrency(accountLimitsDTO.getCurrency());

            accountLimits.setCreatedAt(LocalDateTime.now());
            AccountLimits savedAccountLimits = accountLimitsRepository.save(accountLimits);
            return convertToDTO(savedAccountLimits);
        }
        throw new RuntimeException("Account or Transaction Type not found");
    }

    @Override
    public AccountLimitsDTO getAccountLimitsById(Long limitId) {
        Optional<AccountLimits> accountLimits = accountLimitsRepository.findById(limitId);
        return accountLimits.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<AccountLimitsDTO> getAllAccountLimits() {
        return accountLimitsRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AccountLimitsDTO updateAccountLimits(Long limitId, AccountLimitsDTO accountLimitsDTO) {
        Optional<AccountLimits> existingAccountLimits = accountLimitsRepository.findById(limitId);

        if (existingAccountLimits.isPresent()) {
            AccountLimits accountLimits = existingAccountLimits.get();
            accountLimits.setDailyLimit(accountLimitsDTO.getDailyLimit());
            accountLimits.setTransactionLimit(accountLimitsDTO.getTransactionLimit());
            accountLimits.setCurrency(accountLimitsDTO.getCurrency());


            AccountLimits updatedAccountLimits = accountLimitsRepository.save(accountLimits);
            return convertToDTO(updatedAccountLimits);
        }
        throw new RuntimeException("Account Limits not found");
    }

    @Override
    public void deleteAccountLimits(Long limitId) {
        accountLimitsRepository.deleteById(limitId);
    }

    private AccountLimitsDTO convertToDTO(AccountLimits accountLimits) {
        AccountLimitsDTO dto = new AccountLimitsDTO();
        dto.setLimitId(accountLimits.getLimitId());
        dto.setAccountId(accountLimits.getAccount().getAccountId());
        dto.setTransactionTypeId(accountLimits.getTransactionType().getTransactionTypeId());
        dto.setDailyLimit(accountLimits.getDailyLimit());
        dto.setTransactionLimit(accountLimits.getTransactionLimit());
        dto.setCurrency(accountLimits.getCurrency());
        dto.setCreatedAt(accountLimits.getCreatedAt());
        dto.setUpdatedAt(accountLimits.getUpdatedAt());
        return dto;
    }
}
