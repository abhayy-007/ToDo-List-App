package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entities.User;
import com.example.enums.Roles;
import com.example.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean createUser(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return false;
        }

        String trimmedUsername = user.getUsername().trim();
        if (trimmedUsername.isEmpty() || user.getPassword().trim().isEmpty()) {
            return false;
        }

        if (userRepository.findByUsername(trimmedUsername).isPresent()) {
            return false;
        }

        user.setUsername(trimmedUsername);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Roles.USER);
        userRepository.save(user);

        return true;
    }
}
