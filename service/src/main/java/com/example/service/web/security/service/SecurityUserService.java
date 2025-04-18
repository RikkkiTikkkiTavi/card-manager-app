package com.example.service.web.security.service;

import com.example.service.domain.model.User;
import com.example.service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
