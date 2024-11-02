package com.WingBank.Transactionlimit.exception;

public class ValidationException extends RuntimeException {
    private final String errorCode;
    private final String status;

    // Constructor for basic exception
    public ValidationException(String message) {
        super(message);
        this.errorCode = "INTERNAL_SERVER_ERROR";
        this.status = "500_Internal_Server_Error";
    }

    // Constructor for detailed error responses
    public ValidationException(String message, String errorCode, String status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getStatus() {
        return status;
    }
}
