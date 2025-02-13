package com.petsitter.kt.user.controller.dto;

import com.petsitter.kt.user.service.dto.UserDetailsResult;
import lombok.Builder;

@Builder
public record UserDetailsResponse(
        String username,
        String email,
        String phoneNumber
) {

    public static UserDetailsResponse fromResult(UserDetailsResult result) {
        return UserDetailsResponse.builder()
                .username(result.username())
                .email(result.email())
                .phoneNumber(result.phoneNumber())
                .build();
    }
}
