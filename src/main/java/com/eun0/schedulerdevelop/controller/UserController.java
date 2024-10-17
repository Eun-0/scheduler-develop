package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.user.SignupRequest;
import com.eun0.schedulerdevelop.dto.user.UserResponse;
import com.eun0.schedulerdevelop.dto.user.UserUpdateRequest;
import com.eun0.schedulerdevelop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE
    @PostMapping("/signup")
    public UserResponse signup(@RequestBody @Valid SignupRequest requestDto) {
        return userService.signup(requestDto);
    }

    // READ
    @GetMapping("/{userId}")
    public UserResponse findUserById(@PathVariable("userId") Long id) {
        return userService.findUserById(id);
    }

    // UPDATE
    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable("userId") Long id, @RequestBody @Valid UserUpdateRequest requestDto) {
        return userService.updateUser(id, requestDto);
    }

    // DELETE
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

}
