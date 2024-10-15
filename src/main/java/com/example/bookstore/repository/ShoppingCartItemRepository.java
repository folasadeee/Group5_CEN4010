package com.example.bookstore.repository;

import com.example.bookstore.model.ShoppingCartItem;
import com.example.bookstore.model.ShoppingCartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, ShoppingCartItemId> {
    List<ShoppingCartItem> findByCartUserUserId(Long userId);
    Optional<ShoppingCartItem> findByCartCartIdAndBookISBN(Long cartId, String ISBN);

    List<ShoppingCartItem> findByCartCartId(Long cartId);

}
