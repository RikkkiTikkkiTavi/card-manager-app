package com.example.card_manager_app.web.dto;

import com.example.card_manager_app.domain.model.TransactionType;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
@Data
public class TransactionDto {

    Long id;

    CardDto from;

    CardDto to;

    Long amount;

    TransactionType type;
}
