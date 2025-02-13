package com.petsitter.kt.auth.controller.dto;

import com.petsitter.kt.auth.service.dto.AuthenticateQuery;

public record SigninRequest(
    String email,
    String password
) {

    public AuthenticateQuery toQuery() {
        return AuthenticateQuery.builder()
            .email(email)
            .password(password)
            .build();
    }

}
