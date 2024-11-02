//package com.example.bookstore.controller;
//
//import com.example.bookstore.dto.BookRatingDTO;
//import com.example.bookstore.service.RatingsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/ratings/")
//public class RatingsController {
//
//    @Autowired
//    RatingsService ratingsService;
//
//    public List<BookRatingDTO> getBooksByRating(@RequestParam(required = false) int rating) {
//        return ratingsService.getBooksByRating(rating);
//
//    }
//}
