package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.user.SignupRequest;
import com.eun0.schedulerdevelop.dto.user.UserResponse;
import com.eun0.schedulerdevelop.dto.user.UserUpdateRequest;
import com.eun0.schedulerdevelop.entity.User;
import com.eun0.schedulerdevelop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse signup(SignupRequest requestDto) {
        // RequestDTO -> Entity
        User user = requestDto.toEntity();

        // DB 저장
        User savedUser = userRepository.save(user);

        // Entity -> ResponseDTO
        return UserResponse.from(savedUser);
    }

    public UserResponse findUserById(Long id) {
        // 해당 유저가 DB에 존재하는지 확인
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));

        // Entity -> ResponseDTO
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest requestDto) {
        // 해당 유저가 DB에 존재하는지 확인
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));

        // RequestDTO -> Entity
        user.update(requestDto.getUsername(), requestDto.getEmail());

        // Entity -> ResponseDTO
        return UserResponse.from(user);
    }

    public void deleteUser(Long id) {
        // 해당 유저가 DB에 존재하는지 확인
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));

        userRepository.delete(user);
    }
}
