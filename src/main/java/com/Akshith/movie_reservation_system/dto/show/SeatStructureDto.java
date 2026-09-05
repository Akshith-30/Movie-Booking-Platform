package com.Akshith.movie_reservation_system.dto.show;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatStructureDto {

    @Min(1)
    private int seatCount;

    @Positive
    private double seatPrice;

    @NotBlank
    private String area;
}