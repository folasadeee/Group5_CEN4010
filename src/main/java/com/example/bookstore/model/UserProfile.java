package com.example.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userID) {
        this.userId = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
