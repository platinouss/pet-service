package com.petsitter.kt.admin.controller.dto;

import com.petsitter.kt.admin.service.dto.CodeDetailResult;
import lombok.Builder;

@Builder
public record CodeDetailResponse(
        String codeId,
        String codeName,
        String codeValue,
        Integer sortOrder,
        Boolean isActive
) {

    public static CodeDetailResponse fromResult(CodeDetailResult result) {
        return CodeDetailResponse.builder()
                .codeId(result.codeId())
                .codeName(result.codeName())
                .codeValue(result.codeValue())
                .sortOrder(result.sortOrder())
                .isActive(result.isActive())
                .build();
    }
}
