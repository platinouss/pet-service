package com.petsitter.kt.admin.controller.dto;

import com.petsitter.kt.admin.service.dto.AddCodeCommand;

public record CodeAddRequest(
        String codeId,
        String codeName,
        String description,
        String groupId,
        Integer sortOrder
) {

    public AddCodeCommand toCommand() {
        return AddCodeCommand.builder()
                .codeId(codeId)
                .codeName(codeName)
                .description(description)
                .groupId(groupId)
                .sortOrder(sortOrder)
                .build();
    }
}
