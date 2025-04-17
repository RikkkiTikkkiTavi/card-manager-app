package com.example.card_manager_app.web.dto;

import lombok.Data;

@Data
public class NewTransactionDto {
    String cardNumberFrom;
    String cardNumberTo;
    Long amount;
}
