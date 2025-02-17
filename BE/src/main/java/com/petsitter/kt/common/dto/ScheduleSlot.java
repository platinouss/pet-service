package com.petsitter.kt.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record ScheduleSlot(
        DayOfWeek dayOfWeek,
        List<TimeSlot> timeSlots
) {

    public record TimeSlot(
            @JsonFormat(pattern = "HH:mm")
            LocalTime startTime,

            @JsonFormat(pattern = "HH:mm")
            LocalTime endTime
    ) {
    }
}
