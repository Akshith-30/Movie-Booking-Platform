package com.Akshith.movie_reservation_system.mapper;

import com.Akshith.movie_reservation_system.dto.TheaterDto;
import com.Akshith.movie_reservation_system.entity.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {
    public Theater toEntity(TheaterDto dto){
        return Theater.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }

    public TheaterDto toDto(Theater theater){
        return TheaterDto.builder()
                .id(theater.getId())
                .name(theater.getName())
                .location(theater.getLocation())
                .build();
    }
}
