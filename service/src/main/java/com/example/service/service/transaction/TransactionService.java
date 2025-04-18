package com.example.service.service.transaction;

import com.example.service.domain.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getCardTransactions(Long cardId);

    Transaction createTransaction(Transaction transaction);
}
