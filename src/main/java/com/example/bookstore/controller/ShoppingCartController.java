package com.example.bookstore.controller;

import com.example.bookstore.dto.ShoppingCartItemDTO;
import com.example.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.hateoas.EntityModel;
import java.util.Map;


@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    // GET: all user books in shopping cart ----------------------------------------------------------------------------
    @GetMapping("/{userId}/books")
    public List<EntityModel<ShoppingCartItemDTO>> getBooksInShoppingCart(@PathVariable Long userId) {
        return shoppingCartService.getBooksInShoppingCart(userId);
    }


    // POST: add book to user shopping cart ----------------------------------------------------------------------------
    @PostMapping("/{userId}/add-book")
    public ResponseEntity<Void> addBookToShoppingCart(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request) {
        String isbn = request.get("isbn");
        shoppingCartService.addBookToShoppingCart(userId, isbn);

        return ResponseEntity.ok().build();
    }


    // DELETE: remove book from user shopping cart ---------------------------------------------------------------------
    @DeleteMapping("/{userId}/remove-book")
    public ResponseEntity<Void> removeBookFromShoppingCart(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request) {
        String isbn = request.get("isbn");
        shoppingCartService.removeBookFromShoppingCart(userId, isbn);

        return ResponseEntity.ok().build();
    }

}


