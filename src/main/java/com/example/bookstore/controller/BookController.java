package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookDTO> getAllBooks(@RequestParam(required = false) String genre) {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO(book.getISBN(), book.getTitle());
                    Link detailsLink =
                            linkTo(methodOn(BookController.class)
                                    .getBookByISBN
                                            (book.getISBN()))
                                    .withRel("details");
                    bookDTO.add(detailsLink);

                    return bookDTO;
                })
                .toList();
    }

    @GetMapping("/search")
    public List<BookDTO> getBooksByGenre(@RequestParam(required = false) String genre) {
        genre = genre.trim();
        System.out.println("Searching for genre: " + genre);
        List<Book> books = bookRepository.findByGenreIgnoreCase(genre);

        if (books.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found with genre: " + genre);
        }

        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO(book.getISBN(), book.getTitle(), book.getGenre());
                    Link detailsLink =
                            linkTo(methodOn(BookController.class)
                                    .getBookByISBN
                                            (book.getISBN()))
                                    .withRel("details");
                    bookDTO.add(detailsLink);

                    return bookDTO;
                })
                .toList();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ISBN: " + isbn));
        return ResponseEntity.ok(book);
    }

}