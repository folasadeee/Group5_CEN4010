package com.example.bookstore.repository;

import com.example.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Custom query to find authors by book's ISBN
    @Query("SELECT a FROM Author a JOIN BookAuthors ba ON a.authorId = ba.authorId WHERE ba.isbn = :isbn")
    List<Author> findAuthorsByBookIsbn(@Param("isbn") String isbn);
}
