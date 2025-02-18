package com.petsitter.kt.petsitter.controller.dto;

import com.petsitter.kt.petsitter.service.dto.PetsitterResult;
import lombok.Builder;

import java.util.List;

@Builder
public record PetsitterDetailsResponse(
        Long id,
        String username,
        String availableAnimalType,
        List<PetsitterResult.TimeSlotResult> availableDates,
        String region,
        Integer price,
        String introduce
) {

    public static PetsitterDetailsResponse fromResult(PetsitterResult result) {
        return PetsitterDetailsResponse.builder()
                .id(result.id())
                .username(result.username())
                .availableAnimalType(result.availableAnimalType())
                .availableDates(result.availableDates())
                .region(result.region().getName())
                .price(result.price())
                .introduce(result.introduce())
                .build();
    }
}
