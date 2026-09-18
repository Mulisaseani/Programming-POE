/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpoe;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class ProgPOE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("--- REGISTRATION ---");
        
        System.out.print("Enter First Name: ");
        loginSystem.setFirstName(scanner.nextLine());
        
        System.out.print("Enter Last Name: ");
        loginSystem.setLastName(scanner.nextLine());

        // Username Registration
        String username;
        while (true) {
            System.out.print("Enter a Username: ");
            username = scanner.nextLine();
            if (loginSystem.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        // Password Registration
        String password;
        while (true) {
            System.out.print("Enter a Password: ");
            password = scanner.nextLine();
            if (loginSystem.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        // Cell Phone Registration
        String cellNumber;
        while (true) {
            System.out.print("Enter an International Cell Phone Number (e.g., +27838968976): ");
            cellNumber = scanner.nextLine();
            if (loginSystem.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
            }
        }

        // Finalize Registration
        String regStatus = loginSystem.registerUser(username, password, cellNumber);
        System.out.println(regStatus);

        System.out.println("\n--- LOGIN ---");
        
        // Login Loop
        while (true) {
            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();
            
            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            boolean isSuccess = loginSystem.loginUser(loginUser, loginPass);
            System.out.println(loginSystem.returnLoginStatus(isSuccess));

            if (isSuccess) {
                break; // Exit loop on successful login
            }
        }
        
        scanner.close();
    }
}
