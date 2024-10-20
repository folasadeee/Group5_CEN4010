package com.example.bookstore.repository;

import com.example.bookstore.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    UserProfile findByUsername(String username);
}
