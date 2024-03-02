package com.example.bookshop.controller;

import com.example.bookshop.config.UserHolder;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.User;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.services.Books;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {
    private final UserRepository userRepository;
    private final Books books;
    private final UserHolder userHolder;

    public MainController(UserRepository userRepository, Books books, UserHolder userHolder) {
        this.userRepository = userRepository;
        this.books = books;
        this.userHolder = userHolder;
    }
    @GetMapping
    public String current() {
        return userHolder.getCurrentUser().toString();
    }
    @GetMapping("/fav")
    public ResponseEntity<List<com.example.bookshop.entity.Book>> favoriteBooks() {
        User currentUser = userHolder.getCurrentUser();
        return ResponseEntity.ok(currentUser.getFavoriteBooks());
    }
    @PostMapping("/add")
    public ResponseEntity<List<Book>> addToFavorite(@RequestParam long bookId) {
        Book book = this.books.getBookById(bookId).orElseThrow();
        User currentUser = userHolder.getCurrentUser();

        if (!currentUser.getFavoriteBooks().contains(book)) {
            currentUser.getFavoriteBooks().add(book);
            userRepository.save(currentUser);
            return ResponseEntity.ok(currentUser.getFavoriteBooks());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping("/drop")
    public ResponseEntity<Void> dropFavoriteBook(@RequestParam long bookId) {
        com.example.bookshop.entity.Book bookToRemove = books.getBookById(bookId).orElseThrow();
        User currentUser = userHolder.getCurrentUser();

        if (currentUser.getFavoriteBooks().remove(bookToRemove)) {
            userRepository.save(currentUser);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}