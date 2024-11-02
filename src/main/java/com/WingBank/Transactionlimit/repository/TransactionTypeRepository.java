package com.WingBank.Transactionlimit.repository;

import com.WingBank.Transactionlimit.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
