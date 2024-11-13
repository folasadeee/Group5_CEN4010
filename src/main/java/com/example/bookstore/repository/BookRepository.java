package com.example.bookstore.repository;

import java.util.List;
import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

    @Query("SELECT b FROM Book b JOIN BookAuthors ba ON b.isbn = ba.id.ISBN WHERE ba.id.authorId = :authorId")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);

    List<Book> findByGenreIgnoreCase(String genre);

    @Query("SELECT b FROM Book b ORDER BY b.copiesSold DESC")
    List<Book> findTopTenSellers();
}


