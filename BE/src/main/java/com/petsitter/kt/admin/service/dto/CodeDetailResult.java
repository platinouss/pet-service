package com.petsitter.kt.admin.service.dto;

import com.petsitter.kt.admin.model.CodeDetail;
import lombok.Builder;

@Builder
public record CodeDetailResult(
        String codeId,
        String codeName,
        String codeValue,
        Integer sortOrder,
        Boolean isActive
) {

    public static CodeDetailResult fromEntity(CodeDetail codeDetail) {
        return CodeDetailResult.builder()
                .codeId(codeDetail.getId())
                .codeName(codeDetail.getCodeName())
                .codeValue(codeDetail.getCodeValue())
                .sortOrder(codeDetail.getSortOrder())
                .isActive(codeDetail.isActive())
                .build();
    }
}
