package com.example.card_manager_app.web.dto.mapper;


import com.example.card_manager_app.domain.model.Limit;
import com.example.card_manager_app.web.dto.LimitDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface LimitMapper
        extends Mappable<Limit, LimitDto>
{

}
