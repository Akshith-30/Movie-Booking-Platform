package com.Akshith.movie_reservation_system.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowCapacityDto {
    private Long showId;
    private String movieName;
    private String theaterName;
    private LocalDateTime startTime;
    private int totalSeats;
    private int bookedSeats;
    private double occupancyRate;
    private double revenue;
}
