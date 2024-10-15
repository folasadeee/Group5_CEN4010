package com.example.bookstore.repository;

import com.example.bookstore.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CreditCardRepository extends JpaRepository <CreditCard, String> {
    List<CreditCard> findByUsername(String username);
}
