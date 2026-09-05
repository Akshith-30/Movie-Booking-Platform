package com.Akshith.movie_reservation_system.mapper;

import com.Akshith.movie_reservation_system.dto.user.UserResponseDto;
import com.Akshith.movie_reservation_system.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toResponseDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
