package com.example.card_manager_app.service.limit;

import com.example.card_manager_app.domain.model.Limit;
import com.example.card_manager_app.domain.model.User;
import com.example.card_manager_app.repository.LimitRepository;
import com.example.card_manager_app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class LimitServiceImpl implements LimitService {

    private final UserService userService;

    private final LimitRepository limitRepository;

    @Override
    public Limit setTransactionLimit(Long userId, LocalDateTime from, LocalDateTime to, Long amount) {
        User user = userService.getByUserId(userId);
        Limit limit = Limit.builder()
                .user(user)
                .start(from)
                .end(to)
                .amount(amount)
                .build();
        return limitRepository.save(limit);
    }

    @Override
    public Limit getLimitByUserId(Long userId) {
        User user = userService.getByUserId(userId);
        return limitRepository.findByUser(user).orElse(null);
    }
}
