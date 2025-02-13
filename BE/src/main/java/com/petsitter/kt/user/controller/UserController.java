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

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResponse> userDetails(@PathVariable("id") Long userId) {
        UserDetailsResult result = userService.findUser(userId);
        return ResponseEntity.ok(UserDetailsResponse.fromResult(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> userModify(@PathVariable("id") Long userId, @RequestBody UserModifyRequest request) {
        userService.modifyUser(userId, request.toCommand());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> userRemove(@PathVariable("id") Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request) {
        userService.addUser(request.toCommand());
        return ResponseEntity.ok().build();
    }
}
