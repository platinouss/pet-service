package com.petsitter.kt.pet.model;

import com.petsitter.kt.pet.model.enums.AnimalSize;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "pets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_type", nullable = false)
    private String animalType;

    @Column(nullable = false)
    private AnimalSize size;

    @Column(nullable = false)
    private Integer age;

    private String imageUrl;
}
