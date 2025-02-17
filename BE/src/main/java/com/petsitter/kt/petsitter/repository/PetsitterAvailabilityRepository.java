package com.petsitter.kt.petsitter.repository;

import com.petsitter.kt.petsitter.model.PetsitterAvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsitterAvailabilityRepository extends JpaRepository<PetsitterAvailableTime, Long> {
}
