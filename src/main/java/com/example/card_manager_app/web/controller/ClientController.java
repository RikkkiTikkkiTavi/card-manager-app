package com.example.card_manager_app.web.controller;

import com.example.card_manager_app.domain.model.BlockingRequest;
import com.example.card_manager_app.domain.model.Card;
import com.example.card_manager_app.domain.model.Transaction;
import com.example.card_manager_app.service.client.ClientService;
import com.example.card_manager_app.web.dto.BlockingRequestDto;
import com.example.card_manager_app.web.dto.CardDto;
import com.example.card_manager_app.web.dto.NewTransactionDto;
import com.example.card_manager_app.web.dto.TransactionDto;
import com.example.card_manager_app.web.dto.mapper.BlockingRequestMapper;
import com.example.card_manager_app.web.dto.mapper.CardMapper;
import com.example.card_manager_app.web.dto.mapper.TransactionMapper;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Users")
@ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true))
public class ClientController {

    private final ClientService clientService;

    private final TransactionMapper transactionMapper;

    private final CardMapper cardMapper;

    private final BlockingRequestMapper blockingRequestMapper;

    @PatchMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDto transferToCard(@RequestAttribute("userId") Long userId,
                                         @RequestBody NewTransactionDto dto) {
        Transaction transaction = clientService.transferToCard(
                dto.getCardNumberFrom(),
                dto.getCardNumberTo(),
                userId,
                dto.getAmount());
        return transactionMapper.toDto(transaction);
    }

    @GetMapping("/cards")
    @ResponseStatus(HttpStatus.OK)
    public List<CardDto> getUserCards(@RequestAttribute("userId") Long userId) {
        List<Card> cards = clientService.getCardList(userId);
        return cardMapper.toDto(cards);
    }

    @PostMapping("/requestBlocking/{cardId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BlockingRequestDto createBlockingRequest(@RequestAttribute("userId") Long userId,
                                                    @PathVariable("cardId") Long cardId) {
        BlockingRequest blockingRequest = clientService.requestBlocking(cardId, userId);
        return blockingRequestMapper.toDto(blockingRequest);
    }

    @GetMapping("/transactions/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getUserCardTransaction(@RequestAttribute("userId") Long userId,
                                                       @PathVariable("cardId") Long cardId) {
        List<Transaction> transactions = clientService.getTransactionHistory(cardId, userId);
        return transactionMapper.toDto(transactions);
    }

    @PatchMapping("/withdraw/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDto withdrawMoney(@RequestAttribute("userId") Long userId,
                                        @PathVariable("cardId") Long cardId,
                                        @RequestParam(name = "amount")
                                        Long amount) {
        Transaction transaction = clientService.withdrawMoney(cardId, userId, amount);
        return transactionMapper.toDto(transaction);
    }
}
