package com.example.service.service.client;

import com.example.service.domain.model.BlockingRequest;
import com.example.service.domain.model.Card;
import com.example.service.domain.model.Transaction;

import java.util.List;

public interface ClientService {
    Transaction transferToCard(String cardFrom, String cardTo, Long userId, Long amount);

    List<Card> getCardList(Long userId);

    BlockingRequest requestBlocking(Long cardId, Long userId);

    List<Transaction> getTransactionHistory(Long cardId, Long userId);

    Transaction withdrawMoney(Long cardId, Long userId, Long amount);
}
