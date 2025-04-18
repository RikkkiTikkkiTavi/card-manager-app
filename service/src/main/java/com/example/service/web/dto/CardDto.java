package com.example.service.web.dto;

import com.example.service.domain.model.CardStatus;
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
