package com.movie.dea.service;

import com.movie.dea.dto.RegisterForm;
import com.movie.dea.entity.User;
import com.movie.dea.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void register(RegisterForm form) {
        if (userRepository.existsByUsername(form.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();


        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new RuntimeException("Password do not match");
        }
            user.setUsername(form.getUsername());
            user.setPassword(passwordEncoder.encode(form.getPassword()));
            user.setRole("ROLE_USER");
            user.setPhone("+" + UUID.randomUUID().toString().replace("-", "").substring(0, 14));

            userRepository.save(user);
        }
    }