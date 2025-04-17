package com.example.card_manager_app.repository;

import com.example.card_manager_app.domain.model.Card;
import com.example.card_manager_app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumber(String number);

    boolean existsByNumber(String number);

    boolean existsById(Long id);

    List<Card> findAllByOwner(User owner);
}
