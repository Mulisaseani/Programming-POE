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
        MessageManager manager = new MessageManager();

        System.out.println("--- REGISTRATION ---");

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        loginSystem.setFirstName(firstName);

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        loginSystem.setLastName(lastName);

        // Username Registration[cite: 6]
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

        // Password Registration[cite: 6]
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

        // Cell Phone Registration[cite: 7]
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

        // Finalize Registration[cite: 8]
        String regStatus = loginSystem.registerUser(username, password, cellNumber);
        System.out.println(regStatus);

        System.out.println("\n--- LOGIN ---");

        // Login Loop[cite: 7, 8]
        while (true) {
            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            boolean isSuccess = loginSystem.loginUser(loginUser, loginPass);
            System.out.println(loginSystem.returnLoginStatus(isSuccess));

            if (isSuccess) {
                break;
            }
        }

        // ==========================================
        // PART 2 & PART 3: QuickChat Application[cite: 11, 17]
        // ==========================================

        String fullName = firstName + " " + lastName;
        manager.loadStoredMessagesFromJSON(fullName);

        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;
        int totalMessagesSent = 0;

        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            System.out.print("Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("How many messages would you like to enter? ");
                    int totalToEnter = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < totalToEnter; i++) {
                        System.out.println("\n--- Entering Message " + (i + 1) + " of " + totalToEnter + " ---");
                        Message msg = new Message();

                        // 1. Recipient Cell Validation[cite: 12]
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

                        // 2. Message Text Validation[cite: 12]
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

                        // 3. Auto-generate Message ID and Message Hash[cite: 12]
                        String msgID = msg.generateMessageID();
                        String msgHash = msg.createMessageHash(msgID, i, text);

                        System.out.println("Message ID generated: " + msgID);
                        System.out.println("Message Hash generated: " + msgHash);

                        // 4. Send / Disregard / Store Action[cite: 13]
                        System.out.println("\nChoose action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message in a JSON file");
                        System.out.print("Choice: ");
                        int action = scanner.nextInt();
                        scanner.nextLine();

                        String actionResult = msg.sentMessage(action);
                        System.out.println(actionResult);

                        // Only display details when Sent (action == 1)[cite: 13]
                        if (action == 1) {
                            totalMessagesSent++;
                            manager.addSentMessage(text);

                            System.out.println("\n--- Message Details ---");
                            System.out.println(msg.printMessages());
                        } else if (action == 2) {
                            manager.addDisregardedMessage(text);
                        } else if (action == 3) {
                            manager.addStoredMessage(fullName, msgID, msgHash, recipient, text);
                        }
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

                case 4:
                    // Part 3 Stored Messages Submenu[cite: 17]
                    System.out.println("\n--- STORED MESSAGES ---");
                    System.out.println("a) Display the sender and recipient of all stored messages");
                    System.out.println("b) Display the longest stored message");
                    System.out.println("c) Search for a message ID");
                    System.out.println("d) Search for all messages stored for a particular recipient");
                    System.out.println("e) Delete a message using the message hash");
                    System.out.println("f) Display Stored Messages Report");
                    System.out.print("Select option (a-f): ");
                    String subChoice = scanner.nextLine().toLowerCase();

                    switch (subChoice) {
                        case "a":
                            System.out.println(manager.displaySenderAndRecipient());
                            break;
                        case "b":
                            System.out.println("Longest Stored Message: " + manager.getLongestStoredMessage());
                            break;
                        case "c":
                            System.out.print("Enter Message ID to search: ");
                            String searchID = scanner.nextLine();
                            System.out.println(manager.searchByMessageID(searchID));
                            break;
                        case "d":
                            System.out.print("Enter Recipient Cell Number to search: ");
                            String searchRecipient = scanner.nextLine();
                            System.out.println(manager.searchByRecipient(searchRecipient));
                            break;
                        case "e":
                            System.out.print("Enter Message Hash to delete: ");
                            String deleteHash = scanner.nextLine();
                            System.out.println(manager.deleteMessageByHash(deleteHash));
                            break;
                        case "f":
                            System.out.println(manager.displayStoredMessagesReport());
                            break;
                        default:
                            System.out.println("Invalid option. Please select a, b, c, d, e, or f.");
                            break;
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
                    break;
            }
        }

        scanner.close();
    }
}