/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoe;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Student
 */

// declaration of variables
public class Login {
    
    private String storedUsername;
    private String storedPassword;
    private String firstName;
    private String lastName;
    private String cellPhoneNumber;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Method to check if the username contains an underscore and is <= 5 characters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Method to check password complexity
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    // Method to check if the cell phone number meets international standards
    // Reference for regex logic: https://stackoverflow.com/questions/36256157/regex-for-international-phone-numbers
    public boolean checkCellPhoneNumber(String cellNumber) {
        // Regex ensures it starts with '+' followed by 1 to 3 country code digits, 
        // and then the rest of the number up to 10 digits as per the test data provided.
        String regex = "^\\+\\d{1,3}\\d{9,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }

    // Method to handle user registration messaging
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // Store details if all validations pass
        this.storedUsername = username;
        this.storedPassword = password;
        this.cellPhoneNumber = cellNumber;
        
        return "User registered successfully.";
    }

    // Method to verify login credentials
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // Method to return login status messages
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + " ," + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}