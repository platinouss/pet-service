package com.petsitter.kt.user.service.dto;

import com.petsitter.kt.auth.model.Role;
import com.petsitter.kt.user.model.User;
import lombok.Builder;

@Builder
public record UserDetailsResult(
        Long userId,
        String username,
        String email,
        String phoneNumber,
        Role role
) {

    public static UserDetailsResult fromEntity(User user) {
        return UserDetailsResult.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }
}
