package com.WingBank.Transactionlimit.service;

import com.WingBank.Transactionlimit.dto.AccountDTO;
import com.WingBank.Transactionlimit.model.Account;
import com.WingBank.Transactionlimit.model.Customer;
import com.WingBank.Transactionlimit.repository.AccountRepository;
import com.WingBank.Transactionlimit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements iAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccount_Number());
        account.setAccountType(accountDTO.getAccount_Type());
        account.setBalance(accountDTO.getBalance());
        account.setAccountStatus(accountDTO.getAccount_Status());

        // Retrieve Customer entity by ID and set it in Account
        Optional<Customer> customer = customerRepository.findById(accountDTO.getCustomer_Id());
        customer.ifPresent(account::setCustomer);

        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
        Optional<Account> existingAccount = accountRepository.findById(accountId);

        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setAccountNumber(accountDTO.getAccount_Number());
            account.setAccountType(accountDTO.getAccount_Type());
            account.setBalance(accountDTO.getBalance());
            account.setAccountStatus(accountDTO.getAccount_Status());

            // Update Customer if needed
            Optional<Customer> customer = customerRepository.findById(accountDTO.getCustomer_Id());
            customer.ifPresent(account::setCustomer);

            Account updatedAccount = accountRepository.save(account);
            return convertToDTO(updatedAccount);
        }

        return null;
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    private AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccount_Id(account.getAccountId());
        accountDTO.setAccount_Number(account.getAccountNumber());
        accountDTO.setAccount_Type(account.getAccountType());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccount_Status(account.getAccountStatus());

        // Set customerId in AccountDTO from Account's Customer entity
        if (account.getCustomer() != null) {
            accountDTO.setCustomer_Id(account.getCustomer().getCustomerId());
        }

        return accountDTO;
    }
}
