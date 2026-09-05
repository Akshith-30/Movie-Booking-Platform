package com.Akshith.movie_reservation_system.mapper;

import com.Akshith.movie_reservation_system.dto.reservation.ReservationResponseDto;
import com.Akshith.movie_reservation_system.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final SeatMapper seatMapper;

    public ReservationResponseDto toDto(Reservation reservation){
        return ReservationResponseDto.builder()
                .id(reservation.getId())
                .showId(reservation.getShow().getId())
                .movieName(reservation.getShow().getMovie().getMovieName())
                .theaterName(reservation.getShow().getTheater().getName())
                .showStartTime(reservation.getShow().getStartTime())
                .amountPaid(reservation.getAmountPaid())
                .reservationStatus(reservation.getReservationStatus())
                .createdAt(reservation.getCreatedAt())
                .build();
    }
}
