package com.WingBank.Transactionlimit.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    private String dateOfBirth;

    private String accountStatus; // Could be ACTIVE, SUSPENDED, etc.
}
