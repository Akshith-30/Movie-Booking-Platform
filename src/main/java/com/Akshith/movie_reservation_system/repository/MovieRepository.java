package com.Akshith.movie_reservation_system.repository;

import com.Akshith.movie_reservation_system.entity.Movie;
import com.Akshith.movie_reservation_system.enums.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByMovieNameContainingIgnoreCase(String name);
    List<Movie> findByGenreContaining(MovieGenre genre);
}