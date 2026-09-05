package com.Akshith.movie_reservation_system.service;

import com.Akshith.movie_reservation_system.dto.TheaterDto;
import com.Akshith.movie_reservation_system.entity.Theater;
import com.Akshith.movie_reservation_system.mapper.TheaterMapper;
import com.Akshith.movie_reservation_system.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final TheaterMapper theaterMapper;

    public TheaterDto createTheater(TheaterDto dto) {
        Theater theater = theaterMapper.toEntity(dto);
        return theaterMapper.toDto(theaterRepository.save(theater));
    }

    public TheaterDto updateTheater(Long id, TheaterDto dto) {
        Theater existing = theaterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theater not found"));

        existing.setName(dto.getName());
        existing.setLocation(dto.getLocation());

        return theaterMapper.toDto(theaterRepository.save(existing));
    }

    public void deleteTheater(Long id) {
        if (!theaterRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theater not found");
        }

        theaterRepository.deleteById(id);
    }

    public TheaterDto getTheaterById(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theater not found"));

        return theaterMapper.toDto(theater);
    }

    public List<TheaterDto> findAllTheaters(){
        return theaterRepository.findAll().stream()
                .map(theaterMapper::toDto)
                .toList();
    }
}
