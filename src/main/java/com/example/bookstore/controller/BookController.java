package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
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
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookDTO> getAllBooks() {
       return bookService.getAllBooks();
    }

    @GetMapping("/search")
    public List<BookDTO> getBooksByGenre(@RequestParam(required = false) String genre) {
        return bookService.getBooksByGenre(genre);
    }


    @GetMapping("/top-sellers")
    public List<BookDTO> getTopSellers(){
       return bookService.getTopSellers();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }
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