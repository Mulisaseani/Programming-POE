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
        Login userLogin = new Login();

        System.out.println("=== USER REGISTRATION ===");
        
        System.out.print("Enter First Name: ");
        userLogin.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        userLogin.setLastName(scanner.nextLine());

        System.out.print("Enter Username: ");
        userLogin.setUsername(scanner.nextLine());

        System.out.print("Enter Password: ");
        userLogin.setPassword(scanner.nextLine());

        System.out.print("Enter Cell Phone Number (e.g., +27838968976): ");
        userLogin.setCellPhoneNumber(scanner.nextLine());

        System.out.println("\n--- Registration Status ---");
        String registrationMessage = userLogin.registerUser();
        System.out.println(registrationMessage);

        // Proceed to login only if registration succeeded
        if (userLogin.checkUserName() && userLogin.checkPasswordComplexity() && userLogin.checkCellPhoneNumber()) {
            System.out.println("\n=== USER LOGIN ===");
            
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccess = userLogin.loginUser(loginUsername, loginPassword);
            System.out.println(userLogin.returnLoginStatus(loginSuccess));
        } else {
            System.out.println("\nRegistration failed. Please fix the formatting errors above and run again.");
        }

        scanner.close();
    }
   
    
}
