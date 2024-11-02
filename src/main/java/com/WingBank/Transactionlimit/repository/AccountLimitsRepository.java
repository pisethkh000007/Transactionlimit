package com.WingBank.Transactionlimit.repository;

import com.WingBank.Transactionlimit.model.AccountLimits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLimitsRepository extends JpaRepository<AccountLimits, Long> {
}
