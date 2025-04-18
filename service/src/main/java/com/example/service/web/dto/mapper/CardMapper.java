package com.example.service.web.dto.mapper;


import com.example.service.domain.model.Card;
import com.example.service.web.dto.CardDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CardMapper
        extends Mappable<Card, CardDto>
{
    CardDto toDto(Card card);
}
