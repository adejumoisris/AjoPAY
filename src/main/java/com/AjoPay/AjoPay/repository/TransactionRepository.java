package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
