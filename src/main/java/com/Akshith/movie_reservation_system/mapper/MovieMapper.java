package com.Akshith.movie_reservation_system.mapper;

import com.Akshith.movie_reservation_system.dto.MovieDto;
import com.Akshith.movie_reservation_system.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toEntity(MovieDto dto){
        return Movie.builder()
                .movieName(dto.getMovieName())
                .genre(dto.getGenre())
                .movieLength(dto.getMovieLength())
                .movieLanguage(dto.getMovieLanguage())
                .releaseDate(dto.getReleaseDate())
                .build();
    }

    public MovieDto toDto(Movie movie){
        return MovieDto.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .genre(movie.getGenre())
                .movieLength(movie.getMovieLength())
                .movieLanguage(movie.getMovieLanguage())
                .releaseDate(movie.getReleaseDate())
                .build();
    }
}
