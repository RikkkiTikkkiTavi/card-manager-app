package com.example.card_manager_app.web.dto;

import com.example.card_manager_app.domain.model.CardStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardDto {

    private Long id;

    private UserDto owner;

    private String number;

    private LocalDateTime date;

    private CardStatus cardStatus;

    private Long balance;
}
