package com.petsitter.kt.petsitter.repository;

import com.petsitter.kt.petsitter.model.PetsitterReservation;
import com.petsitter.kt.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetsitterReservationRepository extends JpaRepository<PetsitterReservation, Long> {

    List<PetsitterReservation> findByUser(User user);
}
