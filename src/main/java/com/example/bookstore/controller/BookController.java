package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/genre")
    public List<Book> getBooksByGenre(@RequestParam String genre) {
        genre = genre.trim();
        System.out.println("Searching for genre: " + genre);
        List<Book> books = bookRepository.findByGenreIgnoreCase(genre);

        if (books.isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found with genre: " + genre);
        }

        return books;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}