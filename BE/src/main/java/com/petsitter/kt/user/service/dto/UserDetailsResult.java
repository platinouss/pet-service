package com.petsitter.kt.user.service.dto;

import com.petsitter.kt.user.model.User;
import lombok.Builder;

@Builder
public record UserDetailsResult(
        String username,
        String email,
        String phoneNumber
) {

    public static UserDetailsResult fromEntity(User user) {
        return UserDetailsResult.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
