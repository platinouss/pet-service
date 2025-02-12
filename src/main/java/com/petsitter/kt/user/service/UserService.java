package com.petsitter.kt.user.service;

import com.petsitter.kt.user.model.User;
import com.petsitter.kt.user.repository.UserRepository;
import com.petsitter.kt.user.service.dto.SignupCommand;
import com.petsitter.kt.user.service.dto.UserDetailsResult;
import com.petsitter.kt.user.service.dto.UserModifyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void addUser(SignupCommand command) {
        userRepository.save(command.toEntity());
    }

    @Transactional(readOnly = true)
    public UserDetailsResult findUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return UserDetailsResult.fromEntity(user);
    }

    @Transactional
    public void modifyUser(Long userId, UserModifyCommand command) {
        User user = userRepository.findById(userId).orElseThrow();
        user.modify(command);
    }

    @Transactional
    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
