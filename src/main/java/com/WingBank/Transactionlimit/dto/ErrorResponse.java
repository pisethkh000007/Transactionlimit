package com.WingBank.Transactionlimit.dto;

import java.util.Map;

public class ErrorResponse {
    private String status;          // Status of the response (e.g., "error")
    private String errorCode;      // Specific error code (e.g., "RESOURCE_NOT_FOUND")
    private String message;        // Human-readable error message
    private Map<String, String> fieldErrors; // Optional field errors for validation

    // Constructor for basic error responses
    public ErrorResponse(String status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    // Constructor for validation error responses with field errors
    public ErrorResponse(String status, String errorCode, String message, Map<String, String> fieldErrors) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.status = message;
        this.fieldErrors = fieldErrors;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
