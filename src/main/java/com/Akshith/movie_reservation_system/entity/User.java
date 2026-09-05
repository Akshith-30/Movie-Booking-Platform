package com.Akshith.movie_reservation_system.entity;

import com.Akshith.movie_reservation_system.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    private String name;

    @ToString.Include
    private String username;

    @ToString.Include
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String password;
}