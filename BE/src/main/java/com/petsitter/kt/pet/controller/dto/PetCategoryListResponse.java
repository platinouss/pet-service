package com.petsitter.kt.pet.controller.dto;

import com.petsitter.kt.pet.service.dto.PetCategoryResult;
import lombok.Builder;

@Builder
public record PetCategoryListResponse(
        String codeId,
        String codeName
) {

    public static PetCategoryListResponse fromResult(PetCategoryResult result) {
        return PetCategoryListResponse.builder()
                .codeId(result.codeId())
                .codeName(result.codeName())
                .build();
    }
}
