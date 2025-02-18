package com.petsitter.kt.petsitter.repository;

import com.petsitter.kt.petsitter.model.Petsitter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetsitterRepository extends JpaRepository<Petsitter, Long> {

    @Query("SELECT p FROM Petsitter p JOIN FETCH p.user u JOIN FETCH p.availableTimes av " +
            "ORDER BY p.id ASC")
    List<Petsitter> findPetsitterWithUserAndPetsitterAvailableTime(Pageable pageable);
}
