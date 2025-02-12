package com.petsitter.kt.user.repository;

import com.petsitter.kt.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
