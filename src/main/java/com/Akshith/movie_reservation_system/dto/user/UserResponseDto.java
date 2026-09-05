package com.Akshith.movie_reservation_system.dto.user;

import com.Akshith.movie_reservation_system.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private Role role;
}