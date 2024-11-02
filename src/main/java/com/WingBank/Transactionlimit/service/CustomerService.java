package com.WingBank.Transactionlimit.service;

import com.WingBank.Transactionlimit.dto.CustomerDTO;
import com.WingBank.Transactionlimit.exception.ResourceNotFoundException;
import com.WingBank.Transactionlimit.exception.ValidationException;
import com.WingBank.Transactionlimit.model.Customer;
import com.WingBank.Transactionlimit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{1,9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern FULL_NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{3,50}$");

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        validateCustomerData(customerDTO);

        // Check if the email already exists
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new ValidationException("Email already exists! Please use a different email.", "400_Bad_Request", "VALIDATION_ERROR");
        }

        Customer customer = mapToCustomer(new Customer(), customerDTO);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        validateCustomerFormat(customerDTO);
        return customerRepository.save(mapToCustomer(existingCustomer, customerDTO));
    }

    private void validateCustomerData(CustomerDTO customerDTO) {
        if (customerDTO.getFullName() == null || !FULL_NAME_PATTERN.matcher(customerDTO.getFullName()).matches()) {
            throw new ValidationException("Invalid fullName format! Name should contain only letters and spaces, 3 to 50 characters.");
        }

        if (customerDTO.getPhoneNumber() == null || !PHONE_PATTERN.matcher(customerDTO.getPhoneNumber()).matches()) {
            throw new ValidationException("Invalid phoneNumber format! It should be fewer than 10 digits");
        }

        if (customerDTO.getEmail() == null || !EMAIL_PATTERN.matcher(customerDTO.getEmail()).matches()) {
            throw new ValidationException("Invalid email format!");
        }

        if (customerDTO.getDateOfBirth() != null) {
            try {
                LocalDate.parse(customerDTO.getDateOfBirth());
            } catch (DateTimeParseException ex) {
                throw new ValidationException("Invalid dateOfBirth format! Expected format is YYYY-MM-DD.");
            }
        }

        if (customerDTO.getAccountStatus() == null ||
                (!customerDTO.getAccountStatus().equalsIgnoreCase("ACTIVE") &&
                        !customerDTO.getAccountStatus().equalsIgnoreCase("INACTIVE"))) {
            throw new ValidationException("Invalid accountStatus! Allowed values are 'ACTIVE' or 'INACTIVE'.");
        }
    }

    private void validateCustomerFormat(CustomerDTO customerDTO) {
        if (customerDTO.getFullName() != null && !FULL_NAME_PATTERN.matcher(customerDTO.getFullName()).matches()) {
            throw new ValidationException("Invalid fullName format! Name should contain only letters and spaces, 3 to 50 characters.");
        }

        if (customerDTO.getPhoneNumber() != null && !PHONE_PATTERN.matcher(customerDTO.getPhoneNumber()).matches()) {
            throw new ValidationException("Invalid phoneNumber format! It should be exactly 10 digits.");
        }

        if (customerDTO.getEmail() != null && !EMAIL_PATTERN.matcher(customerDTO.getEmail()).matches()) {
            throw new ValidationException("Invalid email format!");
        }

        if (customerDTO.getDateOfBirth() != null) {
            try {
                LocalDate.parse(customerDTO.getDateOfBirth());
            } catch (DateTimeParseException ex) {
                throw new ValidationException("Invalid dateOfBirth format! Expected format is YYYY-MM-DD.");
            }
        }

        if (customerDTO.getAccountStatus() != null &&
                !customerDTO.getAccountStatus().equalsIgnoreCase("ACTIVE") &&
                !customerDTO.getAccountStatus().equalsIgnoreCase("INACTIVE")) {
            throw new ValidationException("Invalid accountStatus! Allowed values are 'ACTIVE' or 'INACTIVE'.");
        }
    }

    private Customer mapToCustomer(Customer customer, CustomerDTO customerDTO) {
        if (customerDTO.getFullName() != null) customer.setFullName(customerDTO.getFullName());
        if (customerDTO.getEmail() != null) customer.setEmail(customerDTO.getEmail());
        if (customerDTO.getPhoneNumber() != null) customer.setPhoneNumber(customerDTO.getPhoneNumber());
        if (customerDTO.getDateOfBirth() != null) customer.setDateOfBirth(LocalDate.parse(customerDTO.getDateOfBirth()));
        if (customerDTO.getAccountStatus() != null) {
//            customer.setAccountStatus(Customer.AccountStatus.valueOf(customerDTO.getAccountStatus().toUpperCase()));
        }
        return customer;
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
