package com.Akshith.movie_reservation_system.dto.auth;

import lombok.*;

@Data @AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private String username;
    private String role;
}