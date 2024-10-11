package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.model.ShoppingCart;
import com.example.bookstore.repository.PublisherRepository;
import com.example.bookstore.repository.ShoppingCartRepository;
import com.example.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @GetMapping("/{userId}/books")
    public List<Book> getBooksInShoppingCart(@PathVariable Long userId) {
        return shoppingCartService.getBooksInShoppingCart(userId);
    }


}


