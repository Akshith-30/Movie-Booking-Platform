package com.Akshith.movie_reservation_system.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AuthRequestDto {
    @NotBlank private String username;
    @NotBlank private String password;
}