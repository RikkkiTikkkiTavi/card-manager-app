package com.example.service.web.dto;

import com.example.service.domain.model.BlockingStatus;
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
