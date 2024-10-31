package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.user.SignupRequest;
import com.eun0.schedulerdevelop.dto.user.UserResponse;
import com.eun0.schedulerdevelop.dto.user.UserUpdateRequest;
import com.eun0.schedulerdevelop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(
            @RequestBody @Valid SignupRequest requestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signup(requestDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findUserById(
            @PathVariable("userId") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findUserById(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable("userId") Long id,
            @RequestBody @Valid UserUpdateRequest requestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.RESET_CONTENT)
                .body(userService.updateUser(id, requestDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("userId") Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
