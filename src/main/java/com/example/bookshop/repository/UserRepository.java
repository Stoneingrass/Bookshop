package com.example.bookshop.repository;

import com.example.bookshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    Optional<User> UserByEmail(String email);
}
