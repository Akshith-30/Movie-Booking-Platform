package com.Akshith.movie_reservation_system.controller;

import com.Akshith.movie_reservation_system.dto.auth.AuthRequestDto;
import com.Akshith.movie_reservation_system.dto.auth.AuthResponseDto;
import com.Akshith.movie_reservation_system.dto.auth.SignupRequestDto;
import com.Akshith.movie_reservation_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@Valid @RequestBody SignupRequestDto request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}