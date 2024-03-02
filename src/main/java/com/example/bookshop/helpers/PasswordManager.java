package com.example.bookshop.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
public final class PasswordManager {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static boolean check(String raw, String hashed) {
        return encoder.matches(raw, hashed);
    }
}
