package com.Akshith.movie_reservation_system.service;

import com.Akshith.movie_reservation_system.dto.report.ReportSummaryDto;
import com.Akshith.movie_reservation_system.dto.report.ShowCapacityDto;
import com.Akshith.movie_reservation_system.entity.Show;
import com.Akshith.movie_reservation_system.enums.SeatStatus;
import com.Akshith.movie_reservation_system.repository.ReservationRepository;
import com.Akshith.movie_reservation_system.repository.SeatRepository;
import com.Akshith.movie_reservation_system.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportingService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    public ReportSummaryDto getOverallReport(){
        List<ShowCapacityDto> breakdown = showRepository.findAll().stream()
                .map(this::buildShowCapacity)
                .toList();

        double totalRevenue = reservationRepository.getTotalRevenue();
        long totalBookedSeats = breakdown.stream().mapToLong(ShowCapacityDto::getBookedSeats).sum();

        return ReportSummaryDto.builder()
                .totalRevenue(totalRevenue)
                .totalBookedSeats(totalBookedSeats)
                .showBreakdown(breakdown)
                .build();
    }

    public ShowCapacityDto getShowReport(Long showId){
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found"));
        return buildShowCapacity(show);
    }

    private ShowCapacityDto buildShowCapacity(Show show){
        long totalSeats = seatRepository.countByShowId(show.getId());
        long bookedSeats = seatRepository.countByShowIdAndStatus(show.getId(), SeatStatus.BOOKED);
        double revenue = reservationRepository.getRevenueByShow(show.getId());

        double occupancy = totalSeats == 0 ? 0.0 : (double) bookedSeats / totalSeats;

        return ShowCapacityDto.builder()
                .showId(show.getId())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getName())
                .startTime(show.getStartTime())
                .totalSeats((int) totalSeats)
                .bookedSeats((int) bookedSeats)
                .occupancyRate(occupancy)
                .revenue(revenue)
                .build();
    }
}
