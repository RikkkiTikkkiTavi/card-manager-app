package com.example.card_manager_app.web.security.service;

import com.example.card_manager_app.domain.model.User;
import com.example.card_manager_app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final UserService userService;

    @Override
    public SecurityUser loadUserByUsername(
            final String username
    ) {
        User user = userService.getByUsername(username);
        return new SecurityUser(user);
    }

}
