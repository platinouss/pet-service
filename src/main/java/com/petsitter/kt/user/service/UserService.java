package com.petsitter.kt.user.service;

import com.petsitter.kt.user.repository.UserRepository;
import com.petsitter.kt.user.service.dto.SignupCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(SignupCommand command) {
        userRepository.save(command.toEntity());
    }
}
