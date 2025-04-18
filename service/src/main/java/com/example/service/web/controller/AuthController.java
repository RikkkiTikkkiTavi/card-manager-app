package com.example.service.web.controller;

import com.example.service.domain.model.User;
import com.example.service.web.dto.JwtRequest;
import com.example.service.web.dto.JwtResponse;
import com.example.service.web.dto.RegistrationDto;
import com.example.service.web.dto.UserDto;
import com.example.service.web.dto.mapper.UserMapper;
import com.example.service.web.security.service.AuthService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login")
@ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true))
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserMapper userMapper;


    @SecurityRequirements
    @PostMapping("/login")
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @SecurityRequirements
    @PostMapping("/registration")
    public UserDto createNewUser(@RequestBody RegistrationDto registrationUserDto) {
        User user = authService.createNewUser(registrationUserDto);
        return userMapper.toDto(user);
    }
}
