package com.Akshith.movie_reservation_system.mapper;

import com.Akshith.movie_reservation_system.dto.seat.SeatResponseDto;
import com.Akshith.movie_reservation_system.entity.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public SeatResponseDto toDto(Seat seat){
        return SeatResponseDto.builder()
                .id(seat.getId())
                .number(seat.getNumber())
                .price(seat.getPrice())
                .status(seat.getStatus())
                .area(seat.getArea())
                .build();
    }
}
