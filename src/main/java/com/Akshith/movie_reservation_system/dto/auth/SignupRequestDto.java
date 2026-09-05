package com.Akshith.movie_reservation_system.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SignupRequestDto {
    @NotBlank private String name;
    @NotBlank @Size(min = 3, max = 30) private String username;
    @NotBlank @Email private String email;
    @NotBlank @Size(min = 6) private String password;
}