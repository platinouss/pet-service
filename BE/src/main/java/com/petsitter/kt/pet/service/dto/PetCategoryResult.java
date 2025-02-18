package com.petsitter.kt.pet.service.dto;

import com.petsitter.kt.admin.model.CodeDetail;
import lombok.Builder;

@Builder
public record PetCategoryResult(
        String codeId,
        String codeName
) {

    public static PetCategoryResult fromEntity(CodeDetail codeDetail) {
        return PetCategoryResult.builder()
                .codeId(codeDetail.getId())
                .codeName(codeDetail.getCodeName())
                .build();
    }
}
