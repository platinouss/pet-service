package com.petsitter.kt.user.model;

import com.petsitter.kt.petsitter.model.Petsitter;
import com.petsitter.kt.user.service.dto.UserModifyCommand;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Builder.Default
    @Column(nullable = false)
    private boolean isDeleted = false;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Role role;

    @OneToOne(mappedBy = "user")
    private Petsitter petsitter;

    public void modify(UserModifyCommand command) {
        this.username = command.username();
        this.email = command.email();
        this.phoneNumber = command.phoneNumber();
    }
}
