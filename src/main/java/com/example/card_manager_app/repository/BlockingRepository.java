package com.example.card_manager_app.repository;

import com.example.card_manager_app.domain.model.BlockingRequest;
import com.example.card_manager_app.domain.model.BlockingStatus;
import com.example.card_manager_app.domain.model.Card;
import com.example.card_manager_app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockingRepository extends JpaRepository<BlockingRequest, Long> {
    List<BlockingRequest> findAllByStatus(BlockingStatus status);

    List<BlockingRequest> findAllByRequester(User requester);

    Optional<BlockingRequest> findByCardAndStatus(Card card, BlockingStatus status);
}
