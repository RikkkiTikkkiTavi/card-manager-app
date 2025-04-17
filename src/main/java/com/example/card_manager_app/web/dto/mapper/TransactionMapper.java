package com.example.card_manager_app.web.dto.mapper;

import com.example.card_manager_app.domain.model.Transaction;
import com.example.card_manager_app.web.dto.TransactionDto;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = TransactionMapper.class,
        injectionStrategy = CONSTRUCTOR
)
public interface TransactionMapper
        extends Mappable<Transaction, TransactionDto> {
}
