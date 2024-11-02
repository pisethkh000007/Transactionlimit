package com.WingBank.Transactionlimit.controller;

import com.WingBank.Transactionlimit.dto.AccountDTO;
import com.WingBank.Transactionlimit.dto.TransactionTypeDTO;
import com.WingBank.Transactionlimit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.WingBank.Transactionlimit.service.TransactionTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    private TransactionTypeService transactionTypeService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Get a single account by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getAccountById(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        Map<String, Object> response = new HashMap<>();

        if (accountDTO != null) {
            response.put("message", "Account retrieved successfully");
            response.put("data", accountDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Account not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Get all accounts
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getAllTransactionTypes() {
        List<TransactionTypeDTO> transactionTypes = transactionTypeService.getAllTransactionTypes();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Transaction types retrieved successfully");
        response.put("data", transactionTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create a new account
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO createdAccount = accountService.createAccount(accountDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Account created successfully");
        response.put("data", createdAccount);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update an existing account
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO updatedAccount = accountService.updateAccount(accountDTO.getAccount_Id(), accountDTO);
        Map<String, Object> response = new HashMap<>();

        if (updatedAccount != null) {
            response.put("message", "Account updated successfully");
            response.put("data", updatedAccount);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Account not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an account
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteAccount(@RequestBody AccountDTO accountDTO) {
        accountService.deleteAccount(accountDTO.getAccount_Id());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Account deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
