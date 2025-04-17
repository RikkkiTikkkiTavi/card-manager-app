package com.example.card_manager_app.web.dto.mapper;

import com.example.card_manager_app.domain.model.User;
import com.example.card_manager_app.web.dto.RegistrationDto;
import com.example.card_manager_app.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {

    User fromRegistration(RegistrationDto registrationDto);
}
