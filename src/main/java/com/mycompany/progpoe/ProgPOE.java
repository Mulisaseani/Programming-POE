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
        
        // ==========================================
        // PART 2: QuickChat Application Feature
        // ==========================================
        
        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;
        int totalMessagesSent = 0;

        // Main Menu Loop
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    System.out.print("How many messages would you like to enter? ");
                    int totalToEnter = scanner.nextInt();
                    scanner.nextLine(); // consume newline character

                    for (int i = 0; i < totalToEnter; i++) {
                        System.out.println("\n--- Entering Message " + (i + 1) + " of " + totalToEnter + " ---");
                        Message msg = new Message();

                        // 1. Recipient Cell Input & Validation
                        String recipient;
                        while (true) {
                            System.out.print("Enter Recipient Cell Number: ");
                            recipient = scanner.nextLine();
                            String validation = msg.checkRecipientCell(recipient);
                            System.out.println(validation);
                            if (validation.equals("Cell phone number successfully captured.")) {
                                msg.setRecipientCell(recipient);
                                break;
                            }
                        }

                        // 2. Message Text Input & Validation
                        String text;
                        while (true) {
                            System.out.print("Enter Message (Max 250 characters): ");
                            text = scanner.nextLine();
                            String lengthStatus = msg.validateMessageLength(text);
                            if (lengthStatus.equals("Message ready to send.")) {
                                msg.setMessageText(text);
                                break;
                            } else {
                                System.out.println(lengthStatus);
                            }
                        }

                        // 3. Generate Message ID and Message Hash
                        String msgID = msg.generateMessageID();
                        String msgHash = msg.createMessageHash(msgID, i, text);

                        System.out.println("Message ID generated: " + msgID);
                        System.out.println("Message Hash generated: " + msgHash);

                        // 4. Action Choice (Send, Disregard, or Store)
                        System.out.println("\nChoose action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message in a JSON file");
                        System.out.print("Choice: ");
                        int action = scanner.nextInt();
                        scanner.nextLine(); // consume newline character

                        String actionResult = msg.sentMessage(action);
                        System.out.println(actionResult);

                        if (action == 1) {
                            totalMessagesSent++;
                        }

                        // Display full message details
                        System.out.println("\n--- Message Details ---");
                        System.out.println(msg.printMessages());
                    }

                    System.out.println("\nTotal messages sent during this session: " + totalMessagesSent);
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
            }
        }
        
        scanner.close();
    }
}
