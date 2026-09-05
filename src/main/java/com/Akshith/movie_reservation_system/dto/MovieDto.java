package com.Akshith.movie_reservation_system.dto;

import com.Akshith.movie_reservation_system.enums.MovieGenre;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id; 

    @NotBlank
    private String movieName;

    @NotEmpty
    private List<MovieGenre> genre;

    @Min(1)
    private int movieLength;

    @NotBlank
    private String movieLanguage;

    @Future
    private LocalDate releaseDate;
}