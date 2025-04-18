package com.example.service.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LimitDto {

    private Long id;

    private UserDto user;

    private LocalDateTime start;

    private LocalDateTime end;

    private Long amount;
}
