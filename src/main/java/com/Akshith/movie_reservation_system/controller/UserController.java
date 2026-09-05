package com.Akshith.movie_reservation_system.controller;

import com.Akshith.movie_reservation_system.dto.user.PromoteUserRequestDto;
import com.Akshith.movie_reservation_system.dto.user.UserResponseDto;
import com.Akshith.movie_reservation_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SUPER_ADMIN')")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PatchMapping("/promote")
    public ResponseEntity<UserResponseDto> promoteUser(@Valid @RequestBody PromoteUserRequestDto dto){
        return ResponseEntity.ok(userService.promoteToAdmin(dto.getUserId()));
    }
}
