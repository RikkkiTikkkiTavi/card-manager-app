package com.example.service.web.dto.mapper;


import com.example.service.domain.model.Limit;
import com.example.service.web.dto.LimitDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface LimitMapper
        extends Mappable<Limit, LimitDto>
{

}
