package com.example.service.repository;

import com.example.service.domain.model.Card;
import com.example.service.domain.model.User;
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
