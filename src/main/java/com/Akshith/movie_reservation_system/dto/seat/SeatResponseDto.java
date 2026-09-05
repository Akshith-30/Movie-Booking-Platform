package com.Akshith.movie_reservation_system.dto.seat;

import com.Akshith.movie_reservation_system.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SeatResponseDto {
    private Long id;
    private int number;
    private String area;
    private double price;
    private SeatStatus status;
}
