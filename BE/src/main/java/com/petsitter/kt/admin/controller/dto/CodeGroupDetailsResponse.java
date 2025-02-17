package com.petsitter.kt.admin.controller.dto;

import com.petsitter.kt.admin.service.dto.CodeGroupResult;
import lombok.Builder;

@Builder
public record CodeGroupDetailsResponse(
        String groupId,
        String groupName,
        String description
) {

    public static CodeGroupDetailsResponse fromResult(CodeGroupResult result) {
        return CodeGroupDetailsResponse.builder()
                .groupId(result.groupId())
                .groupName(result.groupName())
                .description(result.description())
                .build();
    }
}
