package com.movie.dea.config;

import com.movie.dea.entity.User;
import com.movie.dea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBootstrapConfig {

    /**
     * Creates a default admin when the users table is empty (see movie.bootstrap.* in application.properties).
     */
    @Bean
    ApplicationRunner seedDefaultAdmin(
            UserRepository users,
            PasswordEncoder passwordEncoder,
            @Value("${movie.bootstrap.username:admin1}") String bootstrapUsername,
            @Value("${movie.bootstrap.password:Admin1234}") String bootstrapPassword) {
        return args -> {
            if (users.count() > 0) {
                return;
            }
            User admin = new User();
            admin.setUsername(bootstrapUsername);
            admin.setPassword(passwordEncoder.encode(bootstrapPassword));
            admin.setRole("ROLE_ADMIN");
            admin.setPhone("000000000000000");
            users.save(admin);
        };
    }
}
