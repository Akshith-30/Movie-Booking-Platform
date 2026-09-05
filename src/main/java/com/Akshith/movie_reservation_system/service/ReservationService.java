package com.Akshith.movie_reservation_system.service;

import com.Akshith.movie_reservation_system.dto.reservation.ReservationRequestDto;
import com.Akshith.movie_reservation_system.dto.reservation.ReservationResponseDto;
import com.Akshith.movie_reservation_system.entity.Reservation;
import com.Akshith.movie_reservation_system.entity.Seat;
import com.Akshith.movie_reservation_system.entity.Show;
import com.Akshith.movie_reservation_system.entity.User;
import com.Akshith.movie_reservation_system.enums.ReservationStatus;
import com.Akshith.movie_reservation_system.enums.SeatStatus;
import com.Akshith.movie_reservation_system.exceptions.SeatUnavailableException;
import com.Akshith.movie_reservation_system.mapper.ReservationMapper;
import com.Akshith.movie_reservation_system.repository.ReservationRepository;
import com.Akshith.movie_reservation_system.repository.SeatRepository;
import com.Akshith.movie_reservation_system.repository.ShowRepository;
import com.Akshith.movie_reservation_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;

    @Transactional
    public ReservationResponseDto createReservation(Long userId, ReservationRequestDto dto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Show show = showRepository.findById(dto.getShowId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found"));

        if(show.getStartTime().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot reserve seats for a show that has already started");
        }

        List<Seat> seats = seatRepository.findAllByIdForUpdate(dto.getSeatIdsToReserve());

        if(seats.size() != dto.getSeatIdsToReserve().size()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more seats not found");
        }

        for(Seat seat : seats){
            if(!seat.getShow().equals(show.getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat " + seat.getId() + " does not belong to the requested show");
            }
            if(seat.getStatus() == SeatStatus.BOOKED){
                throw new SeatUnavailableException("Seat " + seat.getNumber() + " (" + seat.getArea() + ") is already booked");
            }
        }

        double amountPaid = seats.stream().mapToDouble(Seat::getPrice).sum();

        seats.forEach(seat -> seat.setStatus(SeatStatus.BOOKED));
        seatRepository.saveAll(seats);

        Reservation reservation = Reservation.builder()
                .user(user)
                .show(show)
                .seatsReserved(seats)
                .amountPaid(amountPaid)
                .reservationStatus(ReservationStatus.BOOKED)
                .createdAt(LocalDateTime.now())
                .build();

        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    public List<ReservationResponseDto> getMyReservations(Long userId){
        return reservationRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Transactional
    public void cancelReservation(Long userId, Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if(!reservation.getUser().getId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only cancel your own request");
        }

        if(reservation.getReservationStatus() == ReservationStatus.CANCELLED){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation is already cancelled");
        }

        if(reservation.getShow().getStartTime().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot cancel a reservation for a show that already started");
        }

        reservation.getSeatsReserved().forEach(seat -> seat.setStatus(SeatStatus.AVAILABLE));
        seatRepository.saveAll(reservation.getSeatsReserved());

        reservation.setReservationStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    public List<ReservationResponseDto> getAllReservations(){
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDto)
                .toList();
    }

}
