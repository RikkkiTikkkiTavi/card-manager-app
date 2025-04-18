package com.example.service.service.client;

import com.example.service.domain.exception.LimitException;
import com.example.service.domain.exception.ResourceUnavailableException;
import com.example.service.domain.exception.TransactionException;
import com.example.service.domain.model.*;
import com.example.service.service.blocking.BlockingService;
import com.example.service.service.card.CardService;
import com.example.service.service.limit.LimitService;
import com.example.service.service.transaction.TransactionService;
import com.example.service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final CardService cardService;

    private final BlockingService blockingService;

    private final TransactionService transactionService;

    private final UserService userService;

    private final LimitService limitService;

    @Override
    public Transaction transferToCard(String cardNumberFrom, String cardNumberTo, Long userId, Long amount) {
        Card cardFrom = cardService.getCardByNumber(cardNumberFrom);
        Card cardTo = cardService.getCardByNumber(cardNumberTo);
        User user = userService.getByUserId(userId);

        checkStatusCard(cardFrom);
        checkStatusCard(cardTo);
        checkUserTransfer(cardFrom, cardTo, user, amount);

        Long currentAmountFrom = cardFrom.getBalance();
        Long currentAmountTo = cardTo.getBalance();

        cardFrom.setBalance(currentAmountFrom - amount);
        cardTo.setBalance(currentAmountTo + amount);

        Transaction transaction = Transaction.builder()
                .from(cardFrom)
                .to(cardTo)
                .amount(amount)
                .type(TransactionType.TRANSFER)
                .build();
        return transactionService.createTransaction(transaction);
    }

    private void checkUserTransfer(Card cardFrom, Card cardTo, User user, Long amount) {
        if (!cardFrom.getOwner().getId().equals(user.getId())) {
            throw new TransactionException("Пользователь не владелец карты отправителя");
        }

        if (!cardTo.getOwner().getId().equals(user.getId())) {
            throw new TransactionException("Пользователь не владелец карты получателя");
        }

        if (cardFrom.getBalance() < amount) {
            throw new TransactionException("Недостаточно средств на карте");
        }

        if (cardFrom.equals(cardTo)) {
            throw new TransactionException("Карта отправителя совпадает с картой получателя");
        }
    }

    @Override
    public List<Card> getCardList(Long userId) {
        return cardService.getCards(userId);
    }

    @Override
    public BlockingRequest requestBlocking(Long cardId, Long userId) {
        Card card = cardService.getById(cardId);
        User user = card.getOwner();

        if (!user.getId().equals(userId)) {
            throw new ResourceUnavailableException("Resource unavailable");
        }

        return blockingService.createBlockingRequest(card, user);
    }

    @Override
    public List<Transaction> getTransactionHistory(Long cardId, Long userId) {
        Card card = cardService.getById(cardId);

        if (!card.getOwner().getId().equals(userId)) {
            throw new ResourceUnavailableException("Resource unavailable");
        }
        return transactionService.getCardTransactions(cardId);
    }

    @Override
    public Transaction withdrawMoney(Long cardId, Long userId, Long amount) {
        Card card = cardService.getById(cardId);

        if (!card.getOwner().getId().equals(userId)) {
            throw new ResourceUnavailableException("Resource unavailable");
        }

        if (card.getBalance() < amount) {
            throw new TransactionException("Недостаточно средств на карте");
        }

        checkLimit(userId, amount);
        checkStatusCard(card);

        Long currentBalance = card.getBalance();
        card.setBalance(currentBalance - amount);

        Transaction transaction = Transaction
                .builder()
                .from(card)
                .to(card)
                .type(TransactionType.WITHDRAWAL)
                .amount(amount)
                .build();

        transactionService.createTransaction(transaction);
        return transaction;
    }

    private void checkLimit(Long userId, Long amount) {
        Limit limit = limitService.getLimitByUserId(userId);
        if (limit != null) {
            Long limitAmount = limit.getAmount();
            LocalDateTime start = limit.getStart();
            LocalDateTime end = limit.getEnd();
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(end) && now.isAfter(start)) {
                if (limitAmount >= amount) {
                    limit.setAmount(limitAmount - amount);
                } else {
                    throw new LimitException("Вы исчерпали лимит на снятие установленный администратором");
                }
            }
        }
    }

    private void checkStatusCard(Card card) {
        CardStatus status = card.getCardStatus();

        checkExpirationDate(card);

        if (status.equals(CardStatus.BLOCKED)) {
            throw new TransactionException("Транзакция невозможна, карта заблокирована");
        }

        if (status.equals(CardStatus.WAITING_FOR_ACTIVATE)) {
            throw new TransactionException("Транзакция невозможна, карта не активирована");
        }

        if (status.equals(CardStatus.EXPIRED)) {
            throw new TransactionException("Транзакция невозможна, карта просрочена");
        }
    }

    private void checkExpirationDate(Card card) {
        if (card.getDate().isBefore(LocalDateTime.now())) {
            cardService.setStatus(CardStatus.EXPIRED, card.getId());
        }
    }
}
