package com.petsitter.kt.petsitter.controller.dto;

import com.petsitter.kt.petsitter.model.enums.Region;
import com.petsitter.kt.petsitter.service.dto.PetsitterResult;
import lombok.Builder;

@Builder
public record PetsitterDetailsResponse(
        String username,
        Region region,
        Integer price
) {

    public static PetsitterDetailsResponse fromResult(PetsitterResult result) {
        return PetsitterDetailsResponse.builder()
                .username(result.username())
                .region(result.region())
                .price(result.price())
                .build();
    }
}
