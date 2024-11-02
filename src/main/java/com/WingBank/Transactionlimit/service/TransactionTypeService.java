package com.WingBank.Transactionlimit.service;

import com.WingBank.Transactionlimit.dto.TransactionTypeDTO;
import com.WingBank.Transactionlimit.model.TransactionType;
import com.WingBank.Transactionlimit.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionTypeService implements ITransactionType {

    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionTypeService(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }


    @Override
    public TransactionTypeDTO createTransactionType(TransactionTypeDTO transactionTypeDTO) {
        TransactionType transactionType = new TransactionType();
        transactionType.setTransactionName(transactionTypeDTO.getTransactionName());
        transactionType.setDescription(transactionTypeDTO.getDescription());

        TransactionType savedTransactionType = transactionTypeRepository.save(transactionType);
        return convertToDTO(savedTransactionType);
    }

    @Override
    public TransactionTypeDTO getTransactionTypeById(Long transactionTypeId) {
        Optional<TransactionType> transactionType = transactionTypeRepository.findById(transactionTypeId);
        return transactionType.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<TransactionTypeDTO> getAllTransactionTypes() {
        List<TransactionType> transactionTypes = transactionTypeRepository.findAll();
        return transactionTypes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TransactionTypeDTO updateTransactionType(Long transactionTypeId, TransactionTypeDTO transactionTypeDTO) {
        Optional<TransactionType> existingTransactionType = transactionTypeRepository.findById(transactionTypeId);
        if (existingTransactionType.isPresent()) {
            TransactionType transactionType = existingTransactionType.get();
            transactionType.setTransactionName(transactionTypeDTO.getTransactionName());
            transactionType.setDescription(transactionTypeDTO.getDescription());

            TransactionType updatedTransactionType = transactionTypeRepository.save(transactionType);
            return convertToDTO(updatedTransactionType);
        }
        return null;
    }

    @Override
    public void deleteTransactionType(Long transactionTypeId) {
        transactionTypeRepository.deleteById(transactionTypeId);
    }

    private TransactionTypeDTO convertToDTO(TransactionType transactionType) {
        TransactionTypeDTO dto = new TransactionTypeDTO();
        dto.setTransactionTypeId(transactionType.getTransactionTypeId());
        dto.setTransactionName(transactionType.getTransactionName());
        dto.setDescription(transactionType.getDescription());
        dto.setCreatedAt(transactionType.getCreatedAt());
        return dto;
    }
}
