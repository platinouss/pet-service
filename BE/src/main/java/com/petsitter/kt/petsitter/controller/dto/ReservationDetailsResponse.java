package com.petsitter.kt.petsitter.controller.dto;

import com.petsitter.kt.petsitter.service.dto.ReservationResult;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ReservationDetailsResponse(
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String impUid
) {

    public static ReservationDetailsResponse fromResult(ReservationResult result) {
        return ReservationDetailsResponse.builder()
                .date(result.date())
                .startTime(result.startTime())
                .endTime(result.endTime())
                .impUid(result.impUid())
                .build();
    }
}
