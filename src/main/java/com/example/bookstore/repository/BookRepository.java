package com.example.bookstore.repository;

import java.util.List;
import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b JOIN BookAuthors ba ON b.ISBN = ba.id.ISBN WHERE ba.id.authorId = :authorId")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);



public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByGenreIgnoreCase(String genre);


    @Query("SELECT b FROM Book b ORDER BY b.copiesSold DESC")
    List<Book> findTopTenSellers();

}