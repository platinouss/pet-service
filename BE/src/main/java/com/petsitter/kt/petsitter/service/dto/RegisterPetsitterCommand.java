package com.petsitter.kt.petsitter.service.dto;

import com.petsitter.kt.common.dto.ScheduleSlot;
import com.petsitter.kt.petsitter.model.enums.AvailableAnimalType;
import com.petsitter.kt.petsitter.model.enums.Region;
import lombok.Builder;

import java.util.List;

@Builder
public record RegisterPetsitterCommand(
        Region region,
        List<AvailableAnimalType> availableAnimalTypes,
        List<ScheduleSlot> availableDates,
        Integer price,
        String profileImageUrl,
        String introduce
) {
}
