package com.example.bookstore.controller;

import com.example.bookstore.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Endpoint to create a new wishlist
    @PostMapping("/create")
    public ResponseEntity<String> createWishlist(@RequestParam Long userId, @RequestParam String name) {
        try {
            wishlistService.createWishlist(userId, name);
            return ResponseEntity.ok("Wishlist created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to add a book to a wishlist
    @PostMapping("/{wishlistId}/books") // Updated path to include wishlistId in the URL
    public ResponseEntity<String> addBookToWishlist(@PathVariable Long wishlistId, @RequestParam String bookId) {
        try {
            wishlistService.addBookToWishlist(wishlistId, bookId);
            return ResponseEntity.ok("Book added to wishlist successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
