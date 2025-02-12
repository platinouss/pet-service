package com.petsitter.kt.user.service.dto;

import lombok.Builder;

@Builder
public record UserModifyCommand(
        String username,
        String email,
        String phoneNumber
) {
}
