package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.user.SignupRequest;
import com.eun0.schedulerdevelop.dto.user.UserResponse;
import com.eun0.schedulerdevelop.dto.user.UserUpdateRequest;
import com.eun0.schedulerdevelop.entity.User;
import com.eun0.schedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    @Transactional
    public UserResponse signup(SignupRequest requestDto) {
        User user = requestDto.toEntity();

        User savedUser = userRepository.save(user);

        return UserResponse.from(savedUser);
    }

    @Transactional
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));

        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));

        user.update(requestDto.getUsername(), requestDto.getEmail());

        return UserResponse.from(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));

        userRepository.delete(user);
    }
}
