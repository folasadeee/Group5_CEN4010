package com.example.bookstore.service;

import com.example.bookstore.dto.BookRatingDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingsService {

    private final RatingsRepository ratingsRepository;

    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public List<BookRatingDTO> getTopBooksByRating(int rating) {
        List<BookRatingDTO> books = ratingsRepository.findDistinctTopByRatingGreaterThan(rating);

        return books.stream()
                .map(book -> new BookRatingDTO(book.getIsbn(), book.getTitle(), book.getAverageRating()))
                .collect(Collectors.toList());
    }

}
