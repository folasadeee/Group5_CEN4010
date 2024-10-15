package com.example.bookstore.model;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingCartItemId implements Serializable {
    private Long cartId;
    private String isbn;

    public ShoppingCartItemId() {}

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItemId that = (ShoppingCartItemId) o;
        return Objects.equals(cartId, that.cartId) && Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, isbn);
    }
}
