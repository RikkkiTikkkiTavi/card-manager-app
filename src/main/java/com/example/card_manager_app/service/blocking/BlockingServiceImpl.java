package com.example.card_manager_app.service.blocking;

import com.example.card_manager_app.domain.exception.ResourceAlreadyExistsException;
import com.example.card_manager_app.domain.exception.ResourceNotFoundException;
import com.example.card_manager_app.domain.model.BlockingRequest;
import com.example.card_manager_app.domain.model.BlockingStatus;
import com.example.card_manager_app.domain.model.Card;
import com.example.card_manager_app.domain.model.User;
import com.example.card_manager_app.repository.BlockingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockingServiceImpl implements BlockingService {

    private final BlockingRepository blockingRepository;

    @Override
    public BlockingRequest createBlockingRequest(Card card, User user) {

        if (getBlockingRequestPending(card) != null) {
            throw new ResourceAlreadyExistsException();
        }

        BlockingRequest newBlockingRequest = BlockingRequest.builder()
                .requester(user)
                .card(card)
                .created(LocalDateTime.now())
                .status(BlockingStatus.PENDING)
                .build();
        return blockingRepository.save(newBlockingRequest);
    }

    @Override
    public List<BlockingRequest> getBlockingRequestsByStatus(BlockingStatus blockingStatus) {
        return blockingRepository.findAllByStatus(blockingStatus);
    }

    @Override
    public BlockingRequest setNewStatusBlockingRequest(Long requestId, BlockingStatus blockingStatus) {
        BlockingRequest blockingRequest =
                blockingRepository.findById(requestId).orElseThrow(ResourceNotFoundException::new);

        blockingRequest.setStatus(blockingStatus);
        return blockingRepository.save(blockingRequest);
    }

    @Override
    public List<BlockingRequest> getUserBlockingRequests(User user) {
        return blockingRepository.findAllByRequester(user);
    }

    @Override
    public BlockingRequest getBlockingRequestPending(Card card) {
        return blockingRepository.findByCardAndStatus(card, BlockingStatus.PENDING).orElse(null);
    }
}
