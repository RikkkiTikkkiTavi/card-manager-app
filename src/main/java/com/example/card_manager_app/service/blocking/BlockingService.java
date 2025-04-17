package com.example.card_manager_app.service.blocking;

import com.example.card_manager_app.domain.model.*;

import java.util.List;

public interface BlockingService {
    BlockingRequest createBlockingRequest(Card card, User user);

    BlockingRequest setNewStatusBlockingRequest(Long requestId, BlockingStatus blockingStatus);

    List<BlockingRequest> getBlockingRequestsByStatus(BlockingStatus blockingStatus);

    List<BlockingRequest> getUserBlockingRequests(User user);

    BlockingRequest getBlockingRequestPending(Card card);
}
