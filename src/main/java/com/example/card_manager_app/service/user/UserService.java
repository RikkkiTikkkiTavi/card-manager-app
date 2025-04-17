package com.example.card_manager_app.service.user;

import com.example.card_manager_app.domain.model.User;

import java.util.List;

public interface UserService {


    User getByUsername(String username);

    User createUser(User user);

    User getByUserId(Long userId);

    void deleteUserById(Long userId);

    List<User> getUsers();
}
