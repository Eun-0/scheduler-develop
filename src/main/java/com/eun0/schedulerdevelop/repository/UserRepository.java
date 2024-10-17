package com.eun0.schedulerdevelop.repository;

import com.eun0.schedulerdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
