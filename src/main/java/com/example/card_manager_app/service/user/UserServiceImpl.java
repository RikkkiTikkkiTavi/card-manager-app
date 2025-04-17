package com.example.card_manager_app.service.user;

import com.example.card_manager_app.domain.exception.ResourceNotFoundException;
import com.example.card_manager_app.domain.model.User;
import com.example.card_manager_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteUserById(Long userId) {
        getByUserId(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
