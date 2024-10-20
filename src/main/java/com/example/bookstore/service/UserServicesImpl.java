package com.example.bookstore.service;
import com.example.bookstore.model.CreditCard;
import com.example.bookstore.model.UserProfile;
import com.example.bookstore.repository.CreditCardRepository;
import com.example.bookstore.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public UserProfile createUser(UserProfile user) {
        return userProfileRepository.save(user);
    }

    @Override
    public UserProfile retrieveUser(String username) {
        return null;
        //TODO: return user
    }

    @Override
    public UserProfile updateUser(String username) {
        return null;
        //TODO: update user
    }
    @Override
    public CreditCard createUserCard(String username, CreditCard card) {
        UserProfile user = userProfileRepository.findByUsername(username);
        if (user != null) {
            card.setUserProfile(user);
            return creditCardRepository.save(card);
        }
        return null;
    }

    public UserProfile createUserProfile(String username, String password, String firstName, String lastName, String address, String email) {
        UserProfile user = new UserProfile();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setEmail(email);

        return createUser(user);
    }

    public CreditCard createUserCard(String cardNum, String expirationInput, String cvv, UserProfile user) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        dateFormat.setLenient(false);
        Date expirationDate = dateFormat.parse(expirationInput);

        CreditCard card = new CreditCard();
        card.setCardNum(cardNum);
        card.setExpirationDate(expirationDate);
        card.setCvv(cvv);
        card.setUserProfile(user);

        creditCardRepository.save(card);
        return card;
    }
}