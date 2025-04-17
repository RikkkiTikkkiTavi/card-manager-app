package com.example.card_manager_app.repository;

import com.example.card_manager_app.domain.model.Limit;
import com.example.card_manager_app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByUser(User user);
}
