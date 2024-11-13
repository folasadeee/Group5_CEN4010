package com.example.bookstore.repository;

import com.example.bookstore.model.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TempUserRepository extends JpaRepository<TempUser, Long> {}
