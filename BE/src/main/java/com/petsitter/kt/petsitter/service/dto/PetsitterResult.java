package com.petsitter.kt.petsitter.service.dto;

import com.petsitter.kt.petsitter.model.Petsitter;
import com.petsitter.kt.petsitter.model.PetsitterAvailableTime;
import com.petsitter.kt.petsitter.model.enums.Region;
import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Builder
public record PetsitterResult(
        Long id,
        String username,
        String availableAnimalType,
        List<TimeSlotResult> availableDates,
        Region region,
        Integer price,
        String introduce
) {

    public static PetsitterResult fromEntity(Petsitter petsitter) {
        return PetsitterResult.builder()
                .id(petsitter.getId())
                .username(petsitter.getUser().getUsername())
                .availableAnimalType(petsitter.getAvailableAnimalType())
                .availableDates(petsitter.getAvailableTimes().stream().map(TimeSlotResult::fromEntity).toList())
                .region(petsitter.getRegion())
                .price(petsitter.getPrice())
                .introduce(petsitter.getIntroduce())
                .build();
    }

    @Builder
    public record TimeSlotResult(
            DayOfWeek dayOfWeek,
            LocalTime startTime,
            LocalTime endTime
    ) {

        public static TimeSlotResult fromEntity(PetsitterAvailableTime availableTimes) {
            return TimeSlotResult.builder()
                    .dayOfWeek(availableTimes.getDayOfWeek())
                    .startTime(availableTimes.getStartTime())
                    .endTime(availableTimes.getEndTime())
                    .build();
        }

    }

}
