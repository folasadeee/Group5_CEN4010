package com.example.bookstore.controller;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }


// Suli
// Retrieve book by ISBN

    @GetMapping("/{isbn}")
    public ResponseEntity<Map<String, Object>> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> book = bookRepository.findById(isbn);
        if (book.isPresent()) {
            // Fetch author(s) associated with the book
            List<Author> authors = authorRepository.findAuthorsByBookIsbn(isbn);

            // Prepare the response with both book and author details
            Map<String, Object> response = new HashMap<>();
            response.put("book", book.get());
            response.put("authors", authors);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all books by author ID
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable Long authorId) {
        List<Book> books = bookRepository.findBooksByAuthorId(authorId);  // Correct repository reference
        if (!books.isEmpty()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}