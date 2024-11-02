package com.example.bookstore.service;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/books")
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

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

    @Transactional
    public int discountBooksByPercentageAndPublisher(@RequestParam(required = true) Double percentage, @RequestParam(required = true) Long publisher_id) {

        if (percentage <= 0 || percentage >= 100) { // Throw error if rating is not between 0 and 100
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Discount percent must be greater than 0 and less than 100");
        }

        if (!publisherRepository.existsById(publisher_id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher with ID " + publisher_id + "not found");
        }
        System.out.println("Updating books with Publisher ID: " + publisher_id + " with Discount Percentage: " + percentage);
        int count = bookRepository.countBooksByPublisherId(publisher_id);
        System.out.println("Books found for publisher ID " + publisher_id + ": " + count);

        int rowsModified = bookRepository.discountBooksByPercentageAndPublisher(percentage, publisher_id);

        System.out.println("Number of Records Updated: " + rowsModified);
        return rowsModified;
    }

    public List<Book> getBooksByPublisherId(@RequestParam Long publisherId) {
        System.out.println("Searching for books with publisherId: " + publisherId);
        return publisherRepository.getBooksByPublisherId(publisherId);
    }

    public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ISBN: " + isbn));
        return ResponseEntity.ok(book);
    }

}