package com.Akshith.movie_reservation_system.repository;

import com.Akshith.movie_reservation_system.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Reservation> findByShowId(Long showId);

    @Query("select coalesce(sum(r.amountPaid), 0) from Reservation r where r.reservationStatus = 'BOOKED'")
    double getTotalRevenue();

    @Query("select coalesce(sum(r.amountPaid), 0) from Reservation r " +
            "where r.reservationStatus = 'BOOKED' and r.show.id = :showId")
    double getRevenueByShow(@Param("showId") Long showId);
}