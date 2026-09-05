package com.Akshith.movie_reservation_system.dto.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ShowResponseDto {
    private Long id;
    private Long movieId;
    private String movieName;
    private Long theaterId;
    private String theaterName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
