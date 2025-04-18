package com.example.service.web.dto;

import lombok.Data;

@Data
public class NewTransactionDto {
    String cardNumberFrom;
    String cardNumberTo;
    Long amount;
}
