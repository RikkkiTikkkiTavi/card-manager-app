package com.example.card_manager_app.service.card;

import com.example.card_manager_app.domain.exception.ResourceAlreadyExistsException;
import com.example.card_manager_app.domain.exception.ResourceNotFoundException;
import com.example.card_manager_app.domain.model.*;
import com.example.card_manager_app.repository.CardRepository;
import com.example.card_manager_app.service.blocking.BlockingService;
import com.example.card_manager_app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final UserService userService;

    private final BlockingService blockingService;

    @Override
    public Card createCard(Long userId) {
        User owner = userService.getByUserId(userId);

        String cardNumber = RandomStringUtils.randomNumeric(16);//TODO маска

        if (cardRepository.existsByNumber(cardNumber)) {
            throw new ResourceAlreadyExistsException();
        }

        Card newCard = Card.builder()
                .cardStatus(CardStatus.WAITING_FOR_ACTIVATE)
                .date(LocalDateTime.now().plusYears(3))
                .balance(10000L)
                .owner(owner)
                .number(cardNumber)
                .build();

        return cardRepository.save(newCard);
    }

    @Override
    public void deleteCard(Long cardId) {
        if (!cardRepository.existsById(cardId)) {
            throw new ResourceNotFoundException();
        }
        cardRepository.deleteById(cardId);
    }

    @Override
    public Card setStatus(CardStatus cardStatus, Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(ResourceNotFoundException::new);

        BlockingRequest blockingRequest = blockingService.getBlockingRequestPending(card);

        if (blockingRequest != null && cardStatus.equals(CardStatus.BLOCKED)) {
            blockingRequest.setStatus(BlockingStatus.CONFIRMED);
        }

        card.setCardStatus(cardStatus);
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getCards(Long userId) {
        User user = userService.getByUserId(userId);
        return cardRepository.findAllByOwner(user);
    }

    @Override
    public Card getById(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Card getCardByNumber(String number) {
        return cardRepository.findByNumber(number).orElseThrow(ResourceNotFoundException::new);
    }
}
