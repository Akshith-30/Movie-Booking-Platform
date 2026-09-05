package com.Akshith.movie_reservation_system.controller;

import com.Akshith.movie_reservation_system.dto.report.ReportSummaryDto;
import com.Akshith.movie_reservation_system.dto.report.ShowCapacityDto;
import com.Akshith.movie_reservation_system.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/reports")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SUPER_ADMIN')")
public class AdminReportController {

    private final ReportingService reportingService;

    @GetMapping
    public ResponseEntity<ReportSummaryDto> getOverallReport(){
        return ResponseEntity.ok(reportingService.getOverallReport());
    }

    @GetMapping("/shows/{showId}")
    public ResponseEntity<ShowCapacityDto> getShowReport(@PathVariable Long showid){
        return ResponseEntity.ok(reportingService.getShowReport(showid));
    }
}
