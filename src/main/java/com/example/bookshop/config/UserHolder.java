package com.example.bookshop.config;

import com.example.bookshop.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class UserHolder {
    private User currentUser;
}
