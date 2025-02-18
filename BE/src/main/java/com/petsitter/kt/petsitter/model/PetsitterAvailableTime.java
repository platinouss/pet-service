package com.petsitter.kt.petsitter.model;

import com.petsitter.kt.common.dto.ScheduleSlot;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@Getter
@Entity
@Table(name = "pet_sitter_availity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetsitterAvailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Petsitter petsitter;

    public static List<PetsitterAvailableTime> create(List<ScheduleSlot> scheduleSlots, Petsitter petsitter) {
        return scheduleSlots.stream()
                .flatMap(scheduleSlot -> scheduleSlot.timeSlots().stream()
                        .map(timeSlot -> PetsitterAvailableTime.builder()
                                .dayOfWeek(scheduleSlot.dayOfWeek())
                                .startTime(timeSlot.startTime())
                                .endTime(timeSlot.endTime())
                                .petsitter(petsitter)
                                .build()))
                .collect(Collectors.toList());
    }
}
