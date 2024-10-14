package com.example.group5_cen4010.profile_management;
import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ProfileManagement {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        UserProfile user = createUser(scnr);
        CreditCard card = createUserCard(scnr, user);

        //TODO: POST to database
    }

    private static UserProfile createUser(Scanner scnr) {
        String username;
        String password;
        String firstName;
        String lastName;
        String address;
        String email;
        String exitKey = "";
        String userResponse = "";

        UserProfile user = null;

        System.out.println("Create a User Profile.");
        while (true) {
            System.out.println("To exit the program, type \"Quit\" or type \"Continue\".");
            exitKey = scnr.nextLine();
            if (exitKey.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Please select a username.");
            username = scnr.nextLine();
            if(username.length() > 100) {
                System.out.println("Username is too long. Enter a new username.");
                username = scnr.nextLine();
            }

            System.out.println("Please select a password.");
            password = scnr.nextLine();
            if(password.length() > 255) {
                System.out.println("Password is too long. Enter a new password.");
                password = scnr.nextLine();
            }

            System.out.println("Please enter your first name.");
            firstName = scnr.nextLine();
            if(firstName.length() > 100) {
                System.out.println("Your first name is too long. Try again.");
                firstName = scnr.nextLine();
            }

            System.out.println("Please enter your last name.");
            lastName = scnr.nextLine();
            if(lastName.length() > 100) {
                System.out.println("Your last name is too long. Try again.");
                lastName = scnr.nextLine();
            }

            System.out.println("Please enter your home address.");
            address = scnr.nextLine();
            if(address.length() > 255) {
                System.out.println("Your home address is too long. Try again.");
                address = scnr.nextLine();
            }

            System.out.println("Please enter your email address.");
            email = scnr.nextLine();
            if(email.length() > 255) {
                System.out.println("Your email address is too long. Try again.");
                email = scnr.nextLine();
            }

            System.out.println("To reiterate, your User Profile will consist of: " +
                    "\n Username: " + username +
                    "\n Password: " + password +
                    "\n First Name: " + firstName +
                    "\n Last Name: " + lastName +
                    "\n Home Address: " + address +
                    "\n Email Address: " + email);

            while (true) {
                System.out.println("Is everything correct? Please answer \"Yes\" or \"No\".");
                userResponse = scnr.nextLine();

                if (userResponse.equalsIgnoreCase("yes")) {
                    user = new UserProfile();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setAddress(address);
                    user.setEmail(email);
                    //TODO: Adjust this later when credit card gets done & to include userID
                    break;

                } else if (userResponse.equalsIgnoreCase("No")) {
                    System.out.println("Please reenter your User Profile information.");
                    break;

                } else {
                    System.out.println("You did not respond with Yes or No.");
                }
            }
        }
        return user;
    }
    private static CreditCard createUserCard(Scanner scnr, UserProfile user) {
        String cardNum;
        Date expirationDate = null;
        String cvv;
        String exitKey = "";
        String userResponse = "";

        CreditCard card = new CreditCard();

        System.out.println("Enter your Credit Card Information.");
        while(true) {
            System.out.println("To exit the program, type \"Quit\" or type \"Continue\".");
            exitKey = scnr.nextLine();
            if (exitKey.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter your credit card number.");
            cardNum = scnr.nextLine();
            if (cardNum.length() != 16 || !cardNum.matches("\\d+")) {
                System.out.println("Invalid Input. Please re-enter your credit card number.");
                cardNum = scnr.nextLine();
            }

            System.out.println("Enter your credit card's expiration date.");
            String expirationInput = scnr.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            dateFormat.setLenient(false);

            while (expirationDate == null) {
                try {
                    expirationDate = dateFormat.parse(expirationInput);
                } catch (ParseException e) {
                    System.out.println("Invalid input. Please re-enter your credit card's expiration date in MM/YY format.");
                    expirationInput = scnr.nextLine();
                }
            }

            System.out.println("Enter your credit card's cvv");
            cvv = scnr.nextLine();
            if(cvv.length() != 3 || !cvv.matches("\\d+")) {
                System.out.println("Invalid input. Please re-enter your credit card's cvv.");
                cvv = scnr.nextLine();
            }

            System.out.println("To reiterate, your credit card information is: " +
                    "\n Credit Card Number: " + cardNum +
                    "\n Expiration Date: " + expirationDate +
                    "\n CVV: " + cvv);

            while(true) {
                System.out.println("Is everything correct? Please answer \"Yes\" or \"No\".");
                userResponse = scnr.nextLine();

                if (userResponse.equalsIgnoreCase("yes")) {
                    card = new CreditCard();
                    card.setCardNum(cardNum);
                    card.setExpirationDate(expirationDate);
                    card.setUserID(user);
                    break;
                }
                else if (userResponse.equalsIgnoreCase("No")) {
                    System.out.println("Please re-enter your credit card information.");
                    break;
                } else {
                    System.out.println("You did not respond with Yes or No.");
                }
            }
        }
        return card;
    }
}
