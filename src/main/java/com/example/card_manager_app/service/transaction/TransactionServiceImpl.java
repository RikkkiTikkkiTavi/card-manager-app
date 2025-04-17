package com.example.card_manager_app.service.transaction;

import com.example.card_manager_app.domain.model.Transaction;
import com.example.card_manager_app.repository.TransactionRepository;
import com.example.card_manager_app.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final CardService cardService;

    @Override
    public List<Transaction> getCardTransactions(Long cardId) {//TODO пагинация
        cardService.getById(cardId);
        return transactionRepository.findAllByFrom_Id(cardId);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
