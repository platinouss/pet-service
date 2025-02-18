package com.petsitter.kt.common.enums;

import lombok.Getter;

@Getter
public enum CodeGroupType {
    ANIMAL_CATEGORY("anmctg"),
    DOG_CATEGORY("dogctg"),
    CAT_CATEGORY("catctg");

    private final String groupId;

    CodeGroupType(String groupId) {
        this.groupId = groupId;
    }
}
