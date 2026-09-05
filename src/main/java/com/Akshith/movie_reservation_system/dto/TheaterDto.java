package com.Akshith.movie_reservation_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;
}