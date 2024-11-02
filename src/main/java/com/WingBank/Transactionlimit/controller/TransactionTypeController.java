package com.WingBank.Transactionlimit.controller;

import com.WingBank.Transactionlimit.dto.TransactionTypeDTO;
import com.WingBank.Transactionlimit.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction-types")
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    @Autowired
    public TransactionTypeController(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    // Get a single transaction type by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getTransactionTypeById(@PathVariable Long id) {
        TransactionTypeDTO transactionTypeDTO = transactionTypeService.getTransactionTypeById(id);
        Map<String, Object> response = new HashMap<>();

        if (transactionTypeDTO != null) {
            response.put("message", "Transaction type retrieved successfully");
            response.put("data", transactionTypeDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Transaction type not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Get all transaction types
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getAllTransactionTypes() {
        List<TransactionTypeDTO> transactionTypes = transactionTypeService.getAllTransactionTypes();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Transaction types retrieved successfully");
        response.put("data", transactionTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create a new transaction type
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTransactionType(@RequestBody TransactionTypeDTO transactionTypeDTO) {
        TransactionTypeDTO createdTransactionType = transactionTypeService.createTransactionType(transactionTypeDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Transaction type created successfully");
        response.put("data", createdTransactionType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update an existing transaction type
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateTransactionType(@PathVariable Long id, @RequestBody TransactionTypeDTO transactionTypeDTO) {
        TransactionTypeDTO updatedTransactionType = transactionTypeService.updateTransactionType(id, transactionTypeDTO);
        Map<String, Object> response = new HashMap<>();

        if (updatedTransactionType != null) {
            response.put("message", "Transaction type updated successfully");
            response.put("data", updatedTransactionType);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
          response.put("message", "Transaction type not found");
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a transaction type
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteTransactionType(@PathVariable Long id) {
        transactionTypeService.deleteTransactionType(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Transaction type deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
