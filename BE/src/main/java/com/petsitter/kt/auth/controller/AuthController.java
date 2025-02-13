package com.petsitter.kt.auth.controller;

import com.petsitter.kt.auth.controller.dto.SigninRequest;
import com.petsitter.kt.auth.service.AuthService;
import com.petsitter.kt.user.service.dto.UserDetailsResult;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final int SESSION_TIMEOUT_SECONDS = 60 * 60 * 24;

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<Void> signin(@RequestBody SigninRequest request, HttpSession session) {
        UserDetailsResult userDetails = authService.authenticate(request.toQuery());
        session.setAttribute("user", userDetails);
        session.setMaxInactiveInterval(SESSION_TIMEOUT_SECONDS);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
