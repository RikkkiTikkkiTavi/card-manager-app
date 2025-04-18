package com.example.service.web.dto.mapper;

import com.example.service.domain.model.BlockingRequest;
import com.example.service.web.dto.BlockingRequestDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface BlockingRequestMapper extends Mappable<BlockingRequest, BlockingRequestDto> {
}
