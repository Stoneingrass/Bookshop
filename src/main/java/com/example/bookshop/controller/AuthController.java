package com.example.bookshop.controller;

import com.example.bookshop.config.UserHolder;
import com.example.bookshop.entity.User;
import com.example.bookshop.services.Users;
import com.example.bookshop.helpers.PasswordManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class AuthController {
    private final Users users;
    private final UserHolder userHolder;
    public AuthController(Users users, UserHolder userHolder) {
        this.users = users;
        this.userHolder = userHolder;
    }
    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody User user) {
        if (users.findUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        User registeredUser = users.addUser(user);
        userHolder.setCurrentUser(registeredUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    @GetMapping("/auth")
    public ResponseEntity<String> signIn(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOptional = users.findUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (PasswordManager.check(password, user.getPassword())) {
                userHolder.setCurrentUser(user);
                return ResponseEntity.status(HttpStatus.OK).body("OK");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
    }
}
