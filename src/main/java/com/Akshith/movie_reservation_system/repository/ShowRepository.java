package com.Akshith.movie_reservation_system.repository;

import com.Akshith.movie_reservation_system.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieId(Long movieId);
    List<Show> findByTheaterId(Long theaterId);
    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Show> findByTheaterIdAndStartTimeLessThanAndEndTimeGreaterThan(Long theaterId, LocalDateTime newEndTime, LocalDateTime newStartTime);
}