package com.petsitter.kt.auth.service.dto;

import lombok.Builder;

@Builder
public record AuthenticateQuery(
    String email,
    String password
) {

}
