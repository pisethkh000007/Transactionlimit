package com.WingBank.Transactionlimit.repository;

import com.WingBank.Transactionlimit.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
