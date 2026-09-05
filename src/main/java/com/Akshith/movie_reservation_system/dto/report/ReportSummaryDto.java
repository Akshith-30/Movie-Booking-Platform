package com.Akshith.movie_reservation_system.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportSummaryDto {
    private double totalRevenue;
    private long totalBookedSeats;
    private List<ShowCapacityDto> showBreakdown;
}
