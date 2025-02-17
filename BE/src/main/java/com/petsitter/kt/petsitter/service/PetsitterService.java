package com.petsitter.kt.petsitter.service;

import com.petsitter.kt.petsitter.model.Petsitter;
import com.petsitter.kt.petsitter.repository.PetsitterRepository;
import com.petsitter.kt.petsitter.service.dto.PetsitterResult;
import com.petsitter.kt.petsitter.service.dto.RegisterPetsitterCommand;
import com.petsitter.kt.user.model.User;
import com.petsitter.kt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetsitterService {

    private final UserRepository userRepository;
    private final PetsitterRepository petsitterRepository;

    @Transactional
    public void registerPetsitter(Long userId, RegisterPetsitterCommand command) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        Petsitter petsitter = Petsitter.createPetsitter(user, command);
        petsitterRepository.save(petsitter);
    }

    @Transactional(readOnly = true)
    public List<PetsitterResult> findPetsitters(Pageable pageable) {
        List<Petsitter> petsitters = petsitterRepository.findPetsitterWithUser(pageable);
        return petsitters.stream().map(PetsitterResult::fromEntity).toList();
    }
}
