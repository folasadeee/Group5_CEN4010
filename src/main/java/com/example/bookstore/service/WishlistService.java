package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.TempUser;
import com.example.bookstore.model.Wishlist;
import com.example.bookstore.model.WishlistItem;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.TempUserRepository;
import com.example.bookstore.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private BookRepository bookRepository;

    // Create a new wishlist
    public void createWishlist(Long userId, String wishlistName) throws Exception {
        Optional<TempUser> user = tempUserRepository.findById(userId);

        if (user.isPresent()) {
            // Check if the user already has 3 wishlists
            if (user.get().getWishlists().size() >= 3) {
                throw new Exception("User cannot have more than 3 wishlists.");
            }

            Wishlist wishlist = new Wishlist();
            wishlist.setName(wishlistName);
            wishlist.setUser(user.get());
            wishlistRepository.save(wishlist);
        } else {
            throw new Exception("User not found.");
        }
    }

    // Add a book to a user's wishlist
    public void addBookToWishlist(Long wishlistId, String bookId) throws Exception {
        Optional<Wishlist> wishlist = wishlistRepository.findById(wishlistId);
        Optional<Book> book = bookRepository.findById(bookId);

        if (wishlist.isPresent() && book.isPresent()) {
            // Check if the book is already in the wishlist
            WishlistItem existingItem = wishlist.get().getItems().stream()
                    .filter(item -> item.getBook().getISBN().equals(book.get().getISBN()))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                // If the book already exists, just increase the quantity
                existingItem.setQuantity(existingItem.getQuantity() + 1);
                // Save the updated item
                // Optionally, you can also save the wishlist if you modify existing items
                wishlistRepository.save(wishlist.get());
            } else {
                // Create a new WishlistItem
                WishlistItem wishlistItem = new WishlistItem();
                wishlistItem.setWishlist(wishlist.get());
                wishlistItem.setBook(book.get());
                wishlistItem.setQuantity(1); // Set initial quantity to 1

                // Save the new WishlistItem
                wishlist.get().getItems().add(wishlistItem);
                wishlistRepository.save(wishlist.get()); // Save the updated wishlist
            }
        } else {
            throw new Exception("Wishlist or Book not found.");
        }
    }
}
