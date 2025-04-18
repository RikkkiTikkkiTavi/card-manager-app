package com.example.service.service.user;

import com.example.service.domain.exception.ResourceNotFoundException;
import com.example.service.domain.model.User;
import com.example.service.repository.UserRepository;
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
