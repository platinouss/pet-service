package com.petsitter.kt.petsitter.model;

import com.petsitter.kt.common.dto.ScheduleSlot;
import com.petsitter.kt.petsitter.model.enums.PetsitterStatus;
import com.petsitter.kt.petsitter.model.enums.Region;
import com.petsitter.kt.petsitter.service.dto.RegisterPetsitterCommand;
import com.petsitter.kt.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Entity
@Table(name = "pet_sitters")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Petsitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String availableAnimalType;

    private Integer price;

    private String profileImageUrl;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Enumerated(EnumType.STRING)
    private PetsitterStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "petsitter", cascade = CascadeType.PERSIST)
    private List<PetsitterAvailableTime> availableTimes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "petsitter")
    private List<PetsitterReservation> reservations = new ArrayList<>();

    public static Petsitter createPetsitter(User user, RegisterPetsitterCommand command) {
        Petsitter petsitter = Petsitter.builder()
                .region(command.region())
                .availableAnimalType(String.join(",", command.availableAnimalTypes()))
                .price(command.price())
                .profileImageUrl(command.profileImageUrl())
                .introduce(command.introduce())
                .status(PetsitterStatus.PENDING)
                .user(user)
                .build();
        petsitter.setAvailabilities(command.availableDates());
        return petsitter;
    }

    private void setAvailabilities(List<ScheduleSlot> availableDates) {
        this.availableTimes.addAll(PetsitterAvailableTime.create(availableDates, this));
    }
}
