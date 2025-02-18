package com.petsitter.kt.petsitter.service;

import com.petsitter.kt.petsitter.model.Petsitter;
import com.petsitter.kt.petsitter.model.PetsitterReservation;
import com.petsitter.kt.petsitter.repository.PetsitterRepository;
import com.petsitter.kt.petsitter.repository.PetsitterReservationRepository;
import com.petsitter.kt.petsitter.service.dto.CreateReservationCommand;
import com.petsitter.kt.petsitter.service.dto.PetsitterResult;
import com.petsitter.kt.petsitter.service.dto.RegisterPetsitterCommand;
import com.petsitter.kt.petsitter.service.dto.ReservationResult;
import com.petsitter.kt.user.model.User;
import com.petsitter.kt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetsitterService {

    private final UserRepository userRepository;
    private final PetsitterRepository petsitterRepository;
    private final PetsitterReservationRepository petsitterReservationRepository;

    @Transactional
    public void registerPetsitter(Long userId, RegisterPetsitterCommand command) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        Petsitter petsitter = Petsitter.createPetsitter(user, command);
        petsitterRepository.save(petsitter);
    }

    @Transactional
    public void createReservation(Long userId, CreateReservationCommand command) {
        Petsitter petsitter = petsitterRepository.findById(command.petsitterId()).orElseThrow(() -> new RuntimeException("펫시터가 존재하지 않습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        petsitterReservationRepository.save(PetsitterReservation.create(petsitter, user, command));
    }

    @Transactional(readOnly = true)
    public List<ReservationResult> findReservations(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        List<PetsitterReservation> reservations = petsitterReservationRepository.findByUser(user);
        return reservations.stream().map(ReservationResult::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PetsitterResult> findPetsitters(Pageable pageable) {
        List<Petsitter> petsitters = petsitterRepository.findPetsitterWithUserAndPetsitterAvailableTime(pageable);
        return petsitters.stream().map(PetsitterResult::fromEntity).toList();
    }
}
