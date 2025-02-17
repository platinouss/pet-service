package com.petsitter.kt.petsitter.service.dto;

import com.petsitter.kt.petsitter.model.Petsitter;
import com.petsitter.kt.petsitter.model.enums.Region;
import lombok.Builder;

@Builder
public record PetsitterResult(
        String username,
        Region region,
        Integer price
) {

    public static PetsitterResult fromEntity(Petsitter petsitter) {
        return PetsitterResult.builder()
                .username(petsitter.getUser().getUsername())
                .region(petsitter.getRegion())
                .price(petsitter.getPrice())
                .build();
    }
}
