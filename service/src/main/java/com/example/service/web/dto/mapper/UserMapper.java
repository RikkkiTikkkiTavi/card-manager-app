package com.example.service.web.dto.mapper;

import com.example.service.domain.model.User;
import com.example.service.web.dto.RegistrationDto;
import com.example.service.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {

    User fromRegistration(RegistrationDto registrationDto);
}
