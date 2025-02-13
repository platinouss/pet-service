package com.petsitter.kt.user.controller.dto;

import com.petsitter.kt.user.service.dto.SignupCommand;

public record SignupRequest(
        String username,
        String password,
        String email,
        String phoneNumber
) {

    public SignupCommand toCommand() {
        return SignupCommand.builder()
                .username(username)
                .password(password)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
