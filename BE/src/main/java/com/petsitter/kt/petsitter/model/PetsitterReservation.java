package com.petsitter.kt.petsitter.model;

import com.petsitter.kt.petsitter.service.dto.CreateReservationCommand;
import com.petsitter.kt.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@SuperBuilder
@Getter
@Entity
@Table(name = "petsitter_reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetsitterReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private String impUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Petsitter petsitter;

    public static PetsitterReservation create(Petsitter petsitter, User user, CreateReservationCommand command) {
        return PetsitterReservation.builder()
                .petsitter(petsitter)
                .date(command.date())
                .startTime(command.startTime())
                .endTime(command.endTime())
                .impUid(command.impUid())
                .user(user)
                .build();
    }
}
