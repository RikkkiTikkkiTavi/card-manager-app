package com.example.card_manager_app.repository;

import com.example.card_manager_app.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByFrom_Id(Long fromId);
}
