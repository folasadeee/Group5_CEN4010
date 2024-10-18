package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookDTO> getAllBooks(@RequestParam(required = false) String genre) {
        if (genre == null ||  genre.trim().isEmpty()) {
            // If no genre query param is present, return a list of all books
            List<Book> books = bookRepository.findAll();
            return books.stream()
                    .map(book -> new BookDTO(book.getISBN(), book.getTitle()))
                    .toList();
        } else {
            // If a genre is specified, return a list of books with ISBN, title and genre
            genre = genre.trim();
            System.out.println("Searching for genre: " + genre);
            List<Book> books = bookRepository.findByGenreIgnoreCase(genre);

            if (books.isEmpty() ) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found with genre: " + genre);
            }

            return books.stream()
                    .map(book -> new BookDTO(book.getISBN(), book.getTitle(), book.getGenre()))
                    .toList();
        }

    }

    @GetMapping("/{isbn}")
    public Optional<Book> getBookByISBN(@PathVariable String isbn) {
        Optional<Book> books = bookRepository.findById(isbn);
        return books;
    }

}