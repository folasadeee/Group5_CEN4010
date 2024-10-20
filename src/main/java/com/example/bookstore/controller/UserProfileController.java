package com.example.bookstore.controller;

import com.example.bookstore.model.CreditCard;
import com.example.bookstore.model.UserProfile;
import com.example.bookstore.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserServices userServices;

    //User profile POST request
    @PostMapping
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfile userProfile) {
       UserProfile createdUser = userServices.createUser(userProfile);
       return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/{username}/cards")
    public ResponseEntity<CreditCard> createCard(@PathVariable String username, @RequestBody CreditCard card) {
        CreditCard createdCard = userServices.createUserCard(username, card);
        return ResponseEntity.ok(createdCard);
    }

    //TODO: GET user

    //TODO: PUT/PATCH user
}
