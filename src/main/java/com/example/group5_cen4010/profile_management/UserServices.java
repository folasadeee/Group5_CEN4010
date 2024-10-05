package com.example.group5_cen4010.profile_management;

public interface UserServices {
    UserProfile createUser(UserProfile user);
    UserProfile retrieveUser(String username);
    UserProfile updateUser(String username);
    UserProfile createUserCard(String username, CreditCard card);
}
