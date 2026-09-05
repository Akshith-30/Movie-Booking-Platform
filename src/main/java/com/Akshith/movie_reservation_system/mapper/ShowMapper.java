package com.Akshith.movie_reservation_system.mapper;

import com.Akshith.movie_reservation_system.dto.show.ShowResponseDto;
import com.Akshith.movie_reservation_system.entity.Movie;
import com.Akshith.movie_reservation_system.entity.Show;
import com.Akshith.movie_reservation_system.entity.Theater;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {
    public Show toEntity(Movie movie, Theater theater, java.time.LocalDateTime startTime, java.time.LocalDateTime endTime){
        return Show.builder()
                .movie(movie)
                .theater(theater)
                .startTime(startTime)
                .endTime(endTime)
                .build();

    }

    public ShowResponseDto toDto(Show show){
        return ShowResponseDto.builder()
                .id(show.getId())
                .movieId(show.getMovie().getId())
                .movieName(show.getMovie().getMovieName())
                .theaterId(show.getTheater().getId())
                .theaterName(show.getTheater().getName())
                .startTime(show.getStartTime())
                .endTime(show.getEndTime())
                .build();
    }
}
