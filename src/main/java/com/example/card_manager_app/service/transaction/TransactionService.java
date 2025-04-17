package com.example.card_manager_app.service.transaction;

import com.example.card_manager_app.domain.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getCardTransactions(Long cardId);

    Transaction createTransaction(Transaction transaction);
}
