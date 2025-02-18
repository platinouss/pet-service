package com.petsitter.kt.petsitter.controller.dto;

import com.petsitter.kt.common.dto.ScheduleSlot;
import com.petsitter.kt.petsitter.model.enums.Region;
import com.petsitter.kt.petsitter.service.dto.RegisterPetsitterCommand;

import java.util.List;

public record PetsitterAddRequest(
        Region region,
        List<String> availableAnimalTypes,
        List<ScheduleSlot> availableDates,
        Integer price,
        String profileImageUrl,
        String introduce
) {

    public RegisterPetsitterCommand toCommand() {
        return RegisterPetsitterCommand.builder()
                .region(region)
                .availableAnimalTypes(availableAnimalTypes)
                .availableDates(availableDates)
                .price(price)
                .profileImageUrl(profileImageUrl)
                .introduce(introduce)
                .build();
    }
}
