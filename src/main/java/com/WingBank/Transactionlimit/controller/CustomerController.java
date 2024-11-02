package com.WingBank.Transactionlimit.controller;

import com.WingBank.Transactionlimit.dto.CustomerDTO;
import com.WingBank.Transactionlimit.model.Customer;
import com.WingBank.Transactionlimit.service.ICustomerService;
import com.WingBank.Transactionlimit.exception.ResourceNotFoundException; // Import for ResourceNotFoundException
import com.WingBank.Transactionlimit.exception.ValidationException; // Import for ValidationException
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Operation(summary = "Get a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found successfully"),
            @ApiResponse(responseCode = "404", description = "Customer with specified ID not found")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.getCustomer(id));
        } catch (ResourceNotFoundException ex) {
            throw ex; // Let the global handler manage it
        } catch (Exception ex) {
            throw new RuntimeException("Failed to fetch customer: " + ex.getMessage());
        }
    }

    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data for creating a customer")
    })
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.status(201).body(customerService.createCustomer(customerDTO));
        } catch (ValidationException ex) {
            throw ex; // Let the global handler manage it
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create customer: " + ex.getMessage());
        }
    }

    @Operation(summary = "Update an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer with specified ID not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data for updating a customer")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
        } catch (ResourceNotFoundException ex) {
            throw ex; // Let the global handler manage it
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update customer: " + ex.getMessage());
        }
    }

    @Operation(summary = "Delete a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer with specified ID not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            throw ex; // Let the global handler manage it
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete customer: " + ex.getMessage());
        }
    }

    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All customers retrieved successfully")
    })
    @GetMapping("/get")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to fetch customers: " + ex.getMessage());
        }
    }
}
