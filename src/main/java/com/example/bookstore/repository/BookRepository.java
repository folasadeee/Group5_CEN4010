package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByGenreIgnoreCase(String genre);


    @Query("SELECT b FROM Book b ORDER BY b.copiesSold DESC")
    List<Book> findTopTenSellers();

}