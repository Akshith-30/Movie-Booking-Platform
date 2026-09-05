package com.Akshith.movie_reservation_system.service;

import com.Akshith.movie_reservation_system.dto.seat.SeatResponseDto;
import com.Akshith.movie_reservation_system.dto.show.SeatStructureDto;
import com.Akshith.movie_reservation_system.dto.show.ShowRequestDto;
import com.Akshith.movie_reservation_system.dto.show.ShowResponseDto;
import com.Akshith.movie_reservation_system.entity.Movie;
import com.Akshith.movie_reservation_system.entity.Seat;
import com.Akshith.movie_reservation_system.entity.Show;
import com.Akshith.movie_reservation_system.entity.Theater;
import com.Akshith.movie_reservation_system.enums.SeatStatus;
import com.Akshith.movie_reservation_system.mapper.SeatMapper;
import com.Akshith.movie_reservation_system.mapper.ShowMapper;
import com.Akshith.movie_reservation_system.repository.MovieRepository;
import com.Akshith.movie_reservation_system.repository.SeatRepository;
import com.Akshith.movie_reservation_system.repository.ShowRepository;
import com.Akshith.movie_reservation_system.repository.TheaterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final SeatRepository seatRepository;
    private final ShowMapper showMapper;
    private final SeatMapper seatMapper;

    @Transactional
    public ShowResponseDto createShow(ShowRequestDto dto){
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        Theater theater = theaterRepository.findById(dto.getTheaterId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theater not found"));

        LocalDateTime startTime = dto.getStartTime();
        LocalDateTime endTime = startTime.plusMinutes(movie.getMovieLength());

        List<Show> conflicts = showRepository.findByTheaterIdAndStartTimeLessThanAndEndTimeGreaterThan(theater.getId(), startTime, endTime);

        if(!conflicts.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Theater already has a show scheduled in this time window");
        }

        Show show = showMapper.toEntity(movie, theater, startTime, endTime);
        Show savedShow = showRepository.save(show);

        List<Seat> seats = new ArrayList<>();
        for(SeatStructureDto structure : dto.getSeats()){
            for(int i = 1; i <= structure.getSeatCount(); i++){
                seats.add(Seat.builder()
                        .show(savedShow)
                        .number(i)
                        .area(structure.getArea())
                        .price(structure.getSeatPrice())
                        .status(SeatStatus.AVAILABLE)
                        .build());

            }
        }
        seatRepository.saveAll(seats);
        return showMapper.toDto(savedShow);
    }

    public List<ShowResponseDto> getShowsByDate(LocalDate date){
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return showRepository.findByStartTimeBetween(start, end).stream()
                .map(showMapper :: toDto)
                .toList();
    }

    public ShowResponseDto getShowById(Long id){
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found"));

        return showMapper.toDto(show);
    }

    public List<SeatResponseDto> getSeatsForShow(Long showId){
        if(!showRepository.existsById(showId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
        }

        return seatRepository.findByShowId(showId).stream()
                .map(seatMapper :: toDto)
                .toList();
    }

    public void deleteShow(Long id){
        if(!showRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
        }

        showRepository.deleteById(id);
    }
}
