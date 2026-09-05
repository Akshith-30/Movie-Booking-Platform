package com.Akshith.movie_reservation_system.dto.reservation;

import com.Akshith.movie_reservation_system.dto.seat.SeatResponseDto;
import com.Akshith.movie_reservation_system.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ReservationResponseDto {
    private Long id;
    private Long showId;
    private String movieName;
    private String theaterName;
    private LocalDateTime showStartTime;
    private List<SeatResponseDto> seats;
    private double amountPaid;
    private ReservationStatus reservationStatus;
    private LocalDateTime createdAt;
}
