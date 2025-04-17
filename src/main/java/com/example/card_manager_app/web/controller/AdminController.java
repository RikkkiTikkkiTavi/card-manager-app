package com.example.card_manager_app.web.controller;

import com.example.card_manager_app.domain.model.*;
import com.example.card_manager_app.service.admin.AdminService;
import com.example.card_manager_app.web.dto.*;
import com.example.card_manager_app.web.dto.mapper.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin")
@ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true))
public class AdminController {

    private final AdminService adminService;

    private final CardMapper cardMapper;

    private final TransactionMapper transactionMapper;

    private final UserMapper userMapper;

    private final LimitMapper limitMapper;

    private final BlockingRequestMapper blockingRequestMapper;

    @PostMapping("/cards/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CardDto createCard(@PathVariable Long userId) {
        Card card = adminService.createCard(userId);
        return cardMapper.toDto(card);
    }

    @DeleteMapping("/cards/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCard(@PathVariable("cardId") Long cardId) {
        adminService.deleteCard(cardId);
        return "Карта удалена";
    }

    @PatchMapping("/cards/{cardId}/block")
    @ResponseStatus(HttpStatus.OK)
    public CardDto blockCard(@PathVariable("cardId") Long cardId) {
        Card card = adminService.blockCard(cardId);
        return cardMapper.toDto(card);
    }

    @PatchMapping("/cards/{cardId}/activate")
    @ResponseStatus(HttpStatus.OK)
    public CardDto activateCard(@PathVariable("cardId") Long cardId) {
        Card card = adminService.activateCard(cardId);
        return cardMapper.toDto(card);
    }

    @GetMapping("/cards/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CardDto> getUserCards(@PathVariable("userId") Long userId) {
        List<Card> cards = adminService.getCards(userId);
        return cardMapper.toDto(cards);
    }

    @GetMapping("/transactions/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getCardTransactions(@PathVariable("cardId") Long cardId) {
        List<Transaction> transactions = adminService.getCardTransactions(cardId);
        return transactionMapper.toDto(transactions);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByAdmin(@PathVariable("userId") Long userId) {
        adminService.deleteUser(userId);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        List<User> users = adminService.getUsersList();
        return userMapper.toDto(users);
    }

    @PostMapping("/limits/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LimitDto setLimit(@PathVariable("userId") Long userId,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                             LocalDateTime start,
                             @RequestParam(name = "end")
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                             LocalDateTime end,
                             @RequestParam(name = "amount")
                             Long amount)
    {
        Limit limit = adminService.setTransactionLimit(userId, start, end, amount);
        return limitMapper.toDto(limit);
    }

    @GetMapping("/blockingRequests")
    @ResponseStatus(HttpStatus.OK)
    public List<BlockingRequestDto> getBlockingRequest() {
        List<BlockingRequest> requests = adminService.getBlockingRequestsByStatusPending();
        return blockingRequestMapper.toDto(requests);
    }
}
