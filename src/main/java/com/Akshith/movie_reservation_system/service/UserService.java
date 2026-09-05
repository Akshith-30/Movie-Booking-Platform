package com.Akshith.movie_reservation_system.service;

import com.Akshith.movie_reservation_system.dto.user.UserResponseDto;
import com.Akshith.movie_reservation_system.entity.User;
import com.Akshith.movie_reservation_system.enums.Role;
import com.Akshith.movie_reservation_system.mapper.UserMapper;
import com.Akshith.movie_reservation_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(userMapper :: toResponseDto)
                .toList();
    }

    public UserResponseDto getUserById(Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toResponseDto(user);
    }

    public UserResponseDto promoteToAdmin(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(user.getRole() == Role.ROLE_SUPER_ADMIN){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change super admin's role");
        }

        user.setRole(Role.ROLE_ADMIN);
        return userMapper.toResponseDto(userRepository.save(user));
    }
}
