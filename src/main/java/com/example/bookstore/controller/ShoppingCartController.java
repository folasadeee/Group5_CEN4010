package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.model.ShoppingCart;
import com.example.bookstore.model.ShoppingCartItem;
import com.example.bookstore.repository.PublisherRepository;
import com.example.bookstore.repository.ShoppingCartRepository;
import com.example.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.CollectionModel;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/{userId}/books")
    public List<EntityModel<Book>> getBooksInShoppingCart(@PathVariable Long userId) {
        return shoppingCartService.getBooksInShoppingCart(userId);
    }

    @PostMapping("/{userId}/add-book")
    public ResponseEntity<Void> addBookToShoppingCart(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request) {
        String isbn = request.get("isbn");
        shoppingCartService.addBookToShoppingCart(userId, isbn);

        /*
        EntityModel<ShoppingCartItem> resource = EntityModel.of(newItem,
                linkTo(methodOn(ShoppingCartController.class).getBooksInShoppingCart(userId)).withSelfRel());

         */

        //return ResponseEntity.ok(resource);
        return ResponseEntity.ok().build();
    }

}


