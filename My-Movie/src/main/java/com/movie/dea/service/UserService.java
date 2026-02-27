package com.movie.dea.service;

import com.movie.dea.dto.RegisterForm;
import com.movie.dea.entity.User;
import com.movie.dea.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

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

        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
//        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}
