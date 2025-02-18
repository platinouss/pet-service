package com.petsitter.kt.pet.model.enums;

import lombok.Getter;

@Getter
public enum AnimalSize {
    SMALL("소형", 0, 10),
    MEDIUM("중형", 10, 25),
    LARGE("대형", 25, 100);

    private final String displayName;
    private final int minWeight;
    private final int maxWeight;

    AnimalSize(String displayName, int minWeight, int maxWeight) {
        this.displayName = displayName;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }
}
