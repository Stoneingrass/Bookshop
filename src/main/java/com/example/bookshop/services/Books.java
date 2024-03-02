package com.example.bookshop.services;

import com.example.bookshop.repository.BookRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Books {
    private final BookRepository bookRepository;

    public Books(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<com.example.bookshop.entity.Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<com.example.bookshop.entity.Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    public com.example.bookshop.entity.Book addBook(@NonNull com.example.bookshop.entity.Book entity) {
        return bookRepository.save(entity);
    }

    public void removeBook(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }
}