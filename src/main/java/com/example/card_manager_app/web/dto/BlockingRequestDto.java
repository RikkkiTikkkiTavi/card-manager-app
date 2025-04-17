package com.example.card_manager_app.web.dto;

import com.example.card_manager_app.domain.model.BlockingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlockingRequestDto {

    private Long id;

    private CardDto card;

    private UserDto requester;

    private LocalDateTime created;

    private BlockingStatus status;
}
