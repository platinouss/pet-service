package com.petsitter.kt.petsitter.service.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record CreateReservationCommand(
        Long petsitterId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String impUid
) {
}
