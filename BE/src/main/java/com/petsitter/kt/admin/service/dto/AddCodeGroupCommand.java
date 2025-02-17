package com.petsitter.kt.admin.service.dto;

import lombok.Builder;

@Builder
public record AddCodeGroupCommand(
        String groupId,
        String groupName,
        String description
) {
}
