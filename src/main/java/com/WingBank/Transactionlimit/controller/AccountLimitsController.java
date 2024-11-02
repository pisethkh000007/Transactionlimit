package com.WingBank.Transactionlimit.controller;

import com.WingBank.Transactionlimit.dto.AccountLimitsDTO;
import com.WingBank.Transactionlimit.service.IAccountLimits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account-limits")
public class AccountLimitsController {

    private final IAccountLimits accountLimitsService;

    @Autowired
    public AccountLimitsController(IAccountLimits accountLimitsService) {
        this.accountLimitsService = accountLimitsService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAccountLimits(@RequestBody AccountLimitsDTO accountLimitsDTO) {
        AccountLimitsDTO createdAccountLimits = accountLimitsService.createAccountLimits(accountLimitsDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Account limits created successfully");
        response.put("data", createdAccountLimits);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getAccountLimitsById(@PathVariable Long id) {
        AccountLimitsDTO accountLimitsDTO = accountLimitsService.getAccountLimitsById(id);
        Map<String, Object> response = new HashMap<>();

        if (accountLimitsDTO != null) {
            response.put("message", "Account limits retrieved successfully");
            response.put("data", accountLimitsDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Account limits not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getAllAccountLimits() {
        List<AccountLimitsDTO> accountLimitsList = accountLimitsService.getAllAccountLimits();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Account limits retrieved successfully");
        response.put("data", accountLimitsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateAccountLimits(@PathVariable Long id, @RequestBody AccountLimitsDTO accountLimitsDTO) {
        AccountLimitsDTO updatedAccountLimits = accountLimitsService.updateAccountLimits(id, accountLimitsDTO);
        Map<String, Object> response = new HashMap<>();

        if (updatedAccountLimits != null) {
            response.put("message", "Account limits updated successfully");
            response.put("data", updatedAccountLimits);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Account limits not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteAccountLimits(@PathVariable Long id) {
        accountLimitsService.deleteAccountLimits(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Account limits deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
