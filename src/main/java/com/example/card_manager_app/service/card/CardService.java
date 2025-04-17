package com.example.card_manager_app.service.card;

import com.example.card_manager_app.domain.model.Card;
import com.example.card_manager_app.domain.model.CardStatus;

import java.util.List;

public interface CardService {

    Card createCard(Long userId);

    void deleteCard(Long cardId);

    Card setStatus(CardStatus cardStatus, Long cardId);

    List<Card> getCards(Long userId);

    Card getById(Long cardId);

    Card getCardByNumber(String number);
}
