package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.ShoppingCart;
import com.example.bookstore.model.ShoppingCartItem;
import com.example.bookstore.model.ShoppingCartItemId;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ShoppingCartItemRepository;
import com.example.bookstore.repository.ShoppingCartRepository;
import com.example.bookstore.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private ShoppingCartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TempUserRepository tempUserRepository;


    public List<EntityModel<Book>> getBooksInShoppingCart(Long userID) {
        ShoppingCart shoppingCart = cartRepository.findByUserUserId(userID)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));

        List<ShoppingCartItem> cartItems = cartItemRepository.findByShoppingCartCartId(shoppingCart.getCartId());

        return cartItems.stream()
                .map(cartItem -> {
                    Book book = cartItem.getBook();

                    Link selfLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(ShoppingCartService.class).getBooksInShoppingCart(userID)
                    ).withSelfRel();

                    return EntityModel.of(book, selfLink);
                })
                .collect(Collectors.toList());
    }

    public void addBookToShoppingCart(Long userId, String isbn) {
        ShoppingCart cart = cartRepository.findByUserUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setUser(tempUserRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found")));
                    return cartRepository.save(newCart);
                });

        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        ShoppingCartItem item = cartItemRepository.findByShoppingCartCartIdAndBookISBN(cart.getCartId(), isbn)
                .orElseGet(() -> {
                    ShoppingCartItem newItem = new ShoppingCartItem();
                    ShoppingCartItemId newId = new ShoppingCartItemId();
                    newId.setCartId(cart.getCartId());
                    newId.setISBN(isbn);
                    newItem.setId(newId);
                    newItem.setShoppingCart(cart);
                    newItem.setBook(book);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
    }

}
