package com.example.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart_items")
@IdClass(ShoppingCartItemId.class)
public class ShoppingCartItem {

    @Id
    @Column(name = "cart_id")
    private Long cartId;

    @Id
    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private ShoppingCart cart;

    @ManyToOne
    @JoinColumn(name = "isbn", insertable = false, updatable = false)
    private Book book;

    // This functionality is outside the scope of this project, therefore its default is one
    @Column
    private Integer quantity = 1;


    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart shoppingCart) {
        this.cart = shoppingCart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

