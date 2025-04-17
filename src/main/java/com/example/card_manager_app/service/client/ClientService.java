package com.example.card_manager_app.service.client;

import com.example.card_manager_app.domain.model.BlockingRequest;
import com.example.card_manager_app.domain.model.Card;
import com.example.card_manager_app.domain.model.Transaction;

import java.util.List;

public interface ClientService {
    Transaction transferToCard(String cardFrom, String cardTo, Long userId, Long amount);

    List<Card> getCardList(Long userId);

    BlockingRequest requestBlocking(Long cardId, Long userId);

    List<Transaction> getTransactionHistory(Long cardId, Long userId);

    Transaction withdrawMoney(Long cardId, Long userId, Long amount);
}
