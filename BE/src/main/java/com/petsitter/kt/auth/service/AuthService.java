package com.petsitter.kt.auth.service;

import com.petsitter.kt.auth.service.dto.AuthenticateQuery;
import com.petsitter.kt.user.model.User;
import com.petsitter.kt.user.repository.UserRepository;
import com.petsitter.kt.user.service.dto.UserDetailsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetailsResult authenticate(AuthenticateQuery query) {
        User user = userRepository.findByEmail(query.email())
            .orElseThrow(() -> new RuntimeException("아이디 또는 패스워드가 일치하지 않습니다."));
        if (!user.getPassword().equals(query.password())) {
            throw new RuntimeException("아이디 또는 패스워드가 일치하지 않습니다.");
        }
        return UserDetailsResult.fromEntity(user);
    }
}
