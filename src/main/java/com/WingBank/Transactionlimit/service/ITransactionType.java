package com.WingBank.Transactionlimit.service;
import com.WingBank.Transactionlimit.dto.TransactionTypeDTO;
import java.util.List;
public interface ITransactionType {
    TransactionTypeDTO createTransactionType(TransactionTypeDTO transactionTypeDTO);
    TransactionTypeDTO getTransactionTypeById(Long transactionTypeId);
    List<TransactionTypeDTO> getAllTransactionTypes();
    TransactionTypeDTO updateTransactionType(Long transactionTypeId, TransactionTypeDTO transactionTypeDTO);
    void deleteTransactionType(Long transactionTypeId);
}
