package com.petsitter.kt.petsitter.service.dto;

import com.petsitter.kt.petsitter.model.PetsitterReservation;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ReservationResult(
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String impUid
) {

    public static ReservationResult fromEntity(PetsitterReservation reservation) {
        return ReservationResult.builder()
                .date(reservation.getDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .impUid(reservation.getImpUid())
                .build();
    }
}
