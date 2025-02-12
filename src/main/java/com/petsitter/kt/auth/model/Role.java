package com.petsitter.kt.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER, PET_SITTER, ADMIN;
}
