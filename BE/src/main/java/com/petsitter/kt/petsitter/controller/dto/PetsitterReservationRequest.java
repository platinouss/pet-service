package com.petsitter.kt.petsitter.controller.dto;

import com.petsitter.kt.petsitter.service.dto.CreateReservationCommand;

import java.time.LocalDate;
import java.time.LocalTime;

public record PetsitterReservationRequest(
        Long petsitterId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String impUid
) {

    public CreateReservationCommand toCommand() {
        return CreateReservationCommand.builder()
                .petsitterId(petsitterId)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .impUid(impUid)
                .build();
    }
}
