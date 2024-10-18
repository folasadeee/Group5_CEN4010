package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}

//Suli
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

