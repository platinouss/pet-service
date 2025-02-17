package com.petsitter.kt.admin.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Entity
@Table(name = "code_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeDetail {

    @Id
    private String id;

    @Column(name = "code_name", nullable = false)
    private String codeName;

    @Column(name = "code_value", nullable = false)
    private String codeValue;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private CodeGroup codeGroup;
}

