package com.register.registrationapp.service;

import com.register.registrationapp.entity.User;
import com.register.registrationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailChecking {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> checkEmail(User user) {
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        return user1;
    }
}
