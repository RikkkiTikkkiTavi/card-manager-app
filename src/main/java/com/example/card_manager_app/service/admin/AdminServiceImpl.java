package com.example.card_manager_app.service.admin;

import com.example.card_manager_app.domain.model.*;
import com.example.card_manager_app.service.blocking.BlockingService;
import com.example.card_manager_app.service.card.CardService;
import com.example.card_manager_app.service.limit.LimitService;
import com.example.card_manager_app.service.transaction.TransactionService;
import com.example.card_manager_app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    private final CardService cardService;

    private final BlockingService blockingService;

    private final TransactionService transactionService;

    private final LimitService limitService;

    @Override
    public Card createCard(Long userId) {
        return cardService.createCard(userId);
    }

    @Override
    public void deleteCard(Long cardId) {
        cardService.deleteCard(cardId);
    }

    @Override
    public Card blockCard(Long cardId) {
        return cardService.setStatus(CardStatus.BLOCKED, cardId);
    }

    @Override
    public Card activateCard(Long cardId) {
        return cardService.setStatus(CardStatus.ACTIVATE, cardId);
    }

    @Override
    public List<Transaction> getCardTransactions(Long cardId) {
        return transactionService.getCardTransactions(cardId);//TODO пагинация
    }

    @Override
    public void deleteUser(Long userId) {
        userService.deleteUserById(userId);
    }

    @Override
    public List<User> getUsersList() {
        return userService.getUsers(); //TODO пагинация
    }

    @Override
    public Limit setTransactionLimit(Long userId, LocalDateTime from, LocalDateTime to, Long amount) {
        return limitService.setTransactionLimit(userId, from, to, amount);
    }

    @Override
    public List<Card> getCards(Long userId) {
        return cardService.getCards(userId);
    }

    @Override
    public List<BlockingRequest> getBlockingRequestsByStatusPending() {
        return blockingService.getBlockingRequestsByStatus(BlockingStatus.PENDING);
    }
}