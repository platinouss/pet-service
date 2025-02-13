package com.petsitter.kt.user.service.dto;

import com.petsitter.kt.user.model.User;
import lombok.Builder;

@Builder
public record SignupCommand(
        String username,
        String password,
        String email,
        String phoneNumber
) {

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
