package com.example.bookshop.controller;

import com.example.bookshop.services.Books;
import com.example.bookshop.helpers.CSVManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final Books books;
    public BooksController(Books books) {
        this.books = books;
    }
    @GetMapping
    public ResponseEntity<List<com.example.bookshop.entity.Book>> getAllBooks() {
        return ResponseEntity.ok(books.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<com.example.bookshop.entity.Book>> getBookById(@PathVariable long id) {
        Optional<com.example.bookshop.entity.Book> book = this.books.getBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }
    @PostMapping("/add")
    public ResponseEntity<com.example.bookshop.entity.Book> addBook(@RequestBody com.example.bookshop.entity.Book book) {
        return ResponseEntity.ok(this.books.addBook(book));
    }
    @DeleteMapping("/drop")
    public ResponseEntity<Void> dropFavoriteBook(@RequestParam long id) {
        if (books.getBookById(id).isPresent()) {
            books.removeBook(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/save")
    public void saveAllBooks(HttpServletResponse response) {
        List<com.example.bookshop.entity.Book> books = this.books.getAllBooks();
        CSVManager.save(books, response);
    }
}