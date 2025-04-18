package com.example.service.service.limit;

import com.example.service.domain.model.Limit;

import java.time.LocalDateTime;

public interface LimitService {
    Limit setTransactionLimit(Long userId, LocalDateTime from, LocalDateTime to, Long amount);

    Limit getLimitByUserId(Long userId);
}
