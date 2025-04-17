package com.example.card_manager_app.web.security.service;

import com.example.card_manager_app.domain.exception.AuthenticationException;
import com.example.card_manager_app.domain.model.Role;
import com.example.card_manager_app.domain.model.User;
import com.example.card_manager_app.repository.RoleRepository;
import com.example.card_manager_app.service.user.UserService;
import com.example.card_manager_app.web.dto.JwtRequest;
import com.example.card_manager_app.web.dto.JwtResponse;
import com.example.card_manager_app.web.dto.RegistrationDto;
import com.example.card_manager_app.web.dto.mapper.UserMapper;
import com.example.card_manager_app.web.security.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SecurityUserService securityUserService;

    private final JwtTokenUtils jwtTokenUtils;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    /**
     * Аутентификация пользователя. В случае успеха возвращаем токен.
     */
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неправильный логин или пароль");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

            SecurityUser securityUser = securityUserService.loadUserByUsername(authRequest.getEmail());

            String token = jwtTokenUtils.generateToken(securityUser);
            return new JwtResponse(token);


    }

    /**
     * Регистрация пользователя
     */
    public User createNewUser(@RequestBody RegistrationDto registrationUserDto) {

        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new AuthenticationException("Пароли не совпадают");
        }

        User user = userMapper.fromRegistration(registrationUserDto);


        String password = passwordEncoder.encode(registrationUserDto.getPassword());
        List<Role> roles = List.of(roleRepository.findByName("ROLE_USER"));

        user.setPassword(password);
        user.setRoles(roles);
        return userService.createUser(user);
    }


}
