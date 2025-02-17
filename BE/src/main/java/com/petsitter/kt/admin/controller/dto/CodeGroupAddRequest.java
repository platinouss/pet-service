package com.petsitter.kt.admin.controller.dto;

import com.petsitter.kt.admin.service.dto.AddCodeGroupCommand;

public record CodeGroupAddRequest(
        String groupId,
        String groupName,
        String description
) {

    public AddCodeGroupCommand toCommand() {
        return AddCodeGroupCommand.builder()
                .groupId(groupId)
                .groupName(groupName)
                .description(description)
                .build();
    }
}
