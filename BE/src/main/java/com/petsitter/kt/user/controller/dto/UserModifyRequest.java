package com.petsitter.kt.user.controller.dto;

import com.petsitter.kt.user.service.dto.UserModifyCommand;

public record UserModifyRequest(
        String username,
        String email,
        String phoneNumber
) {

    public UserModifyCommand toCommand() {
        return UserModifyCommand.builder()
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
