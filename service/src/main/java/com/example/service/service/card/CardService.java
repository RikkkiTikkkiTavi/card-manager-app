package com.example.service.service.card;

import com.example.service.domain.model.Card;
import com.example.service.domain.model.CardStatus;

import java.util.List;

public interface CardService {

    Card createCard(Long userId);

    void deleteCard(Long cardId);

    Card setStatus(CardStatus cardStatus, Long cardId);

    List<Card> getCards(Long userId);

    Card getById(Long cardId);

    Card getCardByNumber(String number);
}
