package com.example.bookstore.service;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/books")
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
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

    public List<BookDTO> getTopSellers(){
        List<Book> books = bookRepository.findTopTenSellers()
                .stream().limit(10)
                .toList();

        return books.stream()
                .map (book -> {
                    BookDTO bookDTO = new BookDTO(book.getISBN(), book.getTitle(), book.getCopiesSold());
                    Link detailsLink = linkTo(methodOn(BookController.class).getBookByISBN(book.getISBN()))
                            .withRel("details");
                    bookDTO.add(detailsLink);
                    return bookDTO;
                }).toList();
    }

    public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ISBN: " + isbn));
        return ResponseEntity.ok(book);
    }

}