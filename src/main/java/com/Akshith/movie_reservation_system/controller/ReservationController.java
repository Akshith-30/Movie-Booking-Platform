package com.Akshith.movie_reservation_system.controller;

import com.Akshith.movie_reservation_system.dto.reservation.ReservationRequestDto;
import com.Akshith.movie_reservation_system.dto.reservation.ReservationResponseDto;
import com.Akshith.movie_reservation_system.security.UserPrincipal;
import com.Akshith.movie_reservation_system.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody ReservationRequestDto dto) {
        return ResponseEntity.ok(reservationService.createReservation(principal.getId(), dto));
    }

    @GetMapping("/me")
    public ResponseEntity<List<ReservationResponseDto>> getMyReservations(
            @AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(reservationService.getMyReservations(principal.getId()));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long id) {
        reservationService.cancelReservation(principal.getId(), id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
}