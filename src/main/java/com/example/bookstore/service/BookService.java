package com.example.bookstore.service;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO(book.getIsbn(), book.getTitle());
                    bookDTO.add(
                            linkTo(methodOn(BookController.class).getBookByIsbn(book.getIsbn())).withRel("details")
                    );
                    return bookDTO;
                })
                .toList();
    }

    public List<BookDTO> getBooksByGenre(String genre) {
        genre = genre.trim();
        List<Book> books = bookRepository.findByGenreIgnoreCase(genre);

        if (books.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found with genre: " + genre);
        }

        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO(book.getIsbn(), book.getTitle(), book.getGenre());
                    bookDTO.add(
                            linkTo(methodOn(BookController.class).getBookByIsbn(book.getIsbn())).withRel("details")
                    );
                    return bookDTO;
                })
                .toList();
    }

    public List<BookDTO> getTopSellers(){
        List<Book> books = bookRepository.findTopTenSellers().stream().limit(10).toList();

        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO(book.getIsbn(), book.getTitle(), book.getCopiesSold());
                    bookDTO.add(
                            linkTo(methodOn(BookController.class).getBookByIsbn(book.getIsbn())).withRel("details")
                    );
                    return bookDTO;
                })
                .toList();
    }

    public Book getBookByISBN(String isbn) {
        isbn = isbn.trim();
        System.out.println("Searching for ISBN: " + isbn);  // Log ISBN before querying
        String finalIsbn = isbn;
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ISBN: " + finalIsbn));
    }



    public void addBook(Book book) {
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN must be provided.");
        }
        bookRepository.save(book);
    }
}
