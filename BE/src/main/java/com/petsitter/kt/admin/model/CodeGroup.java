package com.petsitter.kt.admin.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Entity
@Table(name = "code_groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeGroup {

    @Id
    private String id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "codeGroup", cascade = CascadeType.PERSIST)
    private List<CodeDetail> codeDetails = new ArrayList<>();
}
