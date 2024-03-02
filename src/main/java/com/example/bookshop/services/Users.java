package com.example.bookshop.services;

import com.example.bookshop.config.UserHolder;
import com.example.bookshop.entity.User;
import com.example.bookshop.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Users
{
    private final UserRepository userRepository;
    private final UserHolder userHolder;

    public Users(UserRepository userRepository, UserHolder userHolder) {
        this.userRepository = userRepository;
        this.userHolder = userHolder;
    }

    public User addUser(@NonNull User entity) {
        return userRepository.save(entity);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.UserByEmail(email);
    }

}
