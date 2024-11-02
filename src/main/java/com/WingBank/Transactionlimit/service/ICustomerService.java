package com.WingBank.Transactionlimit.service;

import com.WingBank.Transactionlimit.dto.CustomerDTO;
import com.WingBank.Transactionlimit.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer createCustomer(CustomerDTO customerDTO);
    Customer updateCustomer(Long customerId, CustomerDTO customerDTO);
    Customer getCustomer(Long customerId);
    void deleteCustomer(Long customerId);
    List<Customer> getAllCustomers();
}
