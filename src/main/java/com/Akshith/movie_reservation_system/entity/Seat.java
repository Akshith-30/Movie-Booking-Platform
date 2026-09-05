package com.Akshith.movie_reservation_system.entity;

import com.Akshith.movie_reservation_system.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @Enumerated(value = EnumType.STRING)
    private SeatStatus status;

    private double price;

    @ToString.Include
    private int number;

    @ToString.Include
    private String area;
}