package com.example.service.service.admin;

import com.example.service.domain.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {
    Card createCard(Long userId);

    void deleteCard(Long cardId);

    Card blockCard(Long cardId);

    Card activateCard(Long cardId);

    List<Card> getCards(Long userId);

    List<Transaction> getCardTransactions(Long cardId);

    void deleteUser(Long userId);

    List<User> getUsersList();

    Limit setTransactionLimit(Long userId, LocalDateTime from, LocalDateTime to, Long amount);

    List<BlockingRequest> getBlockingRequestsByStatusPending();
}
