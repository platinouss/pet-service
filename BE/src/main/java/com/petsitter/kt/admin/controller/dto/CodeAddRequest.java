package com.petsitter.kt.admin.controller.dto;

import com.petsitter.kt.admin.service.dto.AddCodeCommand;

public record CodeAddRequest(
        String codeId,
        String codeName,
        String codeValue,
        String groupId,
        Integer sortOrder
) {

    public AddCodeCommand toCommand() {
        return AddCodeCommand.builder()
                .codeId(codeId)
                .codeName(codeName)
                .codeValue(codeValue)
                .groupId(groupId)
                .sortOrder(sortOrder)
                .build();
    }
}
