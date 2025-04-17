package com.example.card_manager_app.web.dto.mapper;


import com.example.card_manager_app.domain.model.Card;
import com.example.card_manager_app.web.dto.CardDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CardMapper
        extends Mappable<Card, CardDto>
{
    CardDto toDto(Card card);
}
