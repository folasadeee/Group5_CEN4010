package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findBooksByGenre(String genre);

}