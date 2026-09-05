package com.Akshith.movie_reservation_system.controller;

import com.Akshith.movie_reservation_system.dto.seat.SeatResponseDto;
import com.Akshith.movie_reservation_system.dto.show.ShowRequestDto;
import com.Akshith.movie_reservation_system.dto.show.ShowResponseDto;
import com.Akshith.movie_reservation_system.service.ShowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ShowResponseDto>> getShowsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(showService.getShowsByDate(date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowResponseDto> getShowById(@PathVariable Long id){
        return  ResponseEntity.ok(showService.getShowById(id));
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<List<SeatResponseDto>> getSeatsForShow(@PathVariable Long id){
        return ResponseEntity.ok(showService.getSeatsForShow(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<ShowResponseDto> createShow(@Valid @RequestBody ShowRequestDto dto){
        return ResponseEntity.ok(showService.createShow(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id){
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}
