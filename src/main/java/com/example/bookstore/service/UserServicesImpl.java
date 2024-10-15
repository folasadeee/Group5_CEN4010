package com.example.bookstore.service;
import com.example.bookstore.model.CreditCard;
import com.example.bookstore.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    //TODO: UserProfileRepository

    @Autowired
    //TODO: CreditCardRepository

    @Override
    public UserProfile createUser(UserProfile user) {
        //TODO: return userprofilerepo
    }

    //TODO: retrieveUser

    //TODO: update user



}
