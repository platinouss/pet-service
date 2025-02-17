package com.petsitter.kt.admin.service.dto;

import lombok.Builder;

@Builder
public record AddCodeCommand(
        String codeId,
        String codeName,
        String description,
        String groupId,
        Integer sortOrder
) {
}
