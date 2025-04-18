package com.example.service.repository;

import com.example.service.domain.model.BlockingRequest;
import com.example.service.domain.model.BlockingStatus;
import com.example.service.domain.model.Card;
import com.example.service.domain.model.User;
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
