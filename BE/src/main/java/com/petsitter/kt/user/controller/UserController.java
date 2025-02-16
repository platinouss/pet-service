package com.petsitter.kt.user.controller;

import com.petsitter.kt.user.controller.dto.SignupRequest;
import com.petsitter.kt.user.controller.dto.UserDetailsResponse;
import com.petsitter.kt.user.controller.dto.UserModifyRequest;
import com.petsitter.kt.user.service.UserService;
import com.petsitter.kt.user.service.dto.UserDetailsResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDetailsResponse> userDetails(@SessionAttribute(name = "userId") Long userId) {
        UserDetailsResult result = userService.findUser(userId);
        return ResponseEntity.ok(UserDetailsResponse.fromResult(result));
    }

    @GetMapping("/email-available")
    public ResponseEntity<Void> checkEmail(@RequestParam(value = "email") String email) {
        userService.isEmailAvailable(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request) {
        userService.addUser(request.toCommand());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/me")
    public ResponseEntity<Void> userModify(@SessionAttribute(name = "userId") Long userId, @Valid @RequestBody UserModifyRequest request) {
        userService.modifyUser(userId, request.toCommand());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> userRemove(@SessionAttribute(name = "userId") Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok().build();
    }
}
