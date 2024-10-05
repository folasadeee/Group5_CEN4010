package com.example.group5_cen4010.profile_management;

import javax.persistence.*;

@Entity
@Table(name = "Users")

public class UserProfile {

    @Id
    @GeneratedValue
    private Long userID;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    //TODO: Create Getters & Setters
    public static void main (String[] args) {
        System.out.println("This is my profile management feature branch.");
    }
}
