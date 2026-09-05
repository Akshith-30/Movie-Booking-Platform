package com.Akshith.movie_reservation_system.service;

import com.Akshith.movie_reservation_system.dto.MovieDto;
import com.Akshith.movie_reservation_system.entity.Movie;
import com.Akshith.movie_reservation_system.mapper.MovieMapper;
import com.Akshith.movie_reservation_system.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieDto createMovie(MovieDto dto){
        Movie movie = movieMapper.toEntity(dto);
        return movieMapper.toDto(movieRepository.save(movie));
    }

    public MovieDto updateMovie(Long id, MovieDto dto){
        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        existing.setMovieName(dto.getMovieName());
        existing.setGenre(dto.getGenre());
        existing.setMovieLength(dto.getMovieLength());
        existing.setMovieLanguage(dto.getMovieLanguage());
        existing.setReleaseDate(dto.getReleaseDate());

        return movieMapper.toDto(movieRepository.save(existing));
    }

    public void deleteMovie(Long id){
        Movie move = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        movieRepository.deleteById(id);
    }

    public MovieDto getMovieById(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        return movieMapper.toDto(movie);
    }

    public List<MovieDto> getAllMovies(){
        return movieRepository.findAll().stream()
                .map(movieMapper::toDto)
                .toList();
    }
}
