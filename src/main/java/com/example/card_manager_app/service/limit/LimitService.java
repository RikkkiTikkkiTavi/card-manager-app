package com.example.card_manager_app.service.limit;

import com.example.card_manager_app.domain.model.Limit;

import java.time.LocalDateTime;

public interface LimitService {
    Limit setTransactionLimit(Long userId, LocalDateTime from, LocalDateTime to, Long amount);

    Limit getLimitByUserId(Long userId);
}
