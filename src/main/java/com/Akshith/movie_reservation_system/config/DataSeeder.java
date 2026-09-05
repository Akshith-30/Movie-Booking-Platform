package com.Akshith.movie_reservation_system.config;

import com.Akshith.movie_reservation_system.entity.User;
import com.Akshith.movie_reservation_system.enums.Role;
import com.Akshith.movie_reservation_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.default.username}")
    private String defaultAdminUsername;

    @Value("${admin.default.password}")
    private String defaultAdminPassword;

    @Value("${admin.default.email}")
    private String defaultAdminEmail;

    @Override
    public void run(String... args) {
        if (userRepository.existsByUsername(defaultAdminUsername)) return;

        User admin = User.builder()
                .name("Super Admin")
                .username(defaultAdminUsername)
                .email(defaultAdminEmail)
                .password(passwordEncoder.encode(defaultAdminPassword))
                .role(Role.ROLE_SUPER_ADMIN)
                .build();

        userRepository.save(admin);
    }
}