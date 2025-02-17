package com.petsitter.kt.admin.service.dto;

import com.petsitter.kt.admin.model.CodeGroup;
import lombok.Builder;

@Builder
public record CodeGroupResult(
        String groupId,
        String groupName,
        String description
) {

    public static CodeGroupResult fromEntity(CodeGroup codeGroup) {
        return CodeGroupResult.builder()
                .groupId(codeGroup.getId())
                .groupName(codeGroup.getGroupName())
                .description(codeGroup.getDescription())
                .build();
    }
}
