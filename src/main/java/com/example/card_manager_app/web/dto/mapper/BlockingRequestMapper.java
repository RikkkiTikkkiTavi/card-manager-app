package com.example.card_manager_app.web.dto.mapper;

import com.example.card_manager_app.domain.model.BlockingRequest;
import com.example.card_manager_app.web.dto.BlockingRequestDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface BlockingRequestMapper extends Mappable<BlockingRequest, BlockingRequestDto> {
}
