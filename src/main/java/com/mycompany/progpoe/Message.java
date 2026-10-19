/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoe;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class Message {

    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;

    // Checks if the message ID is no more than 10 characters long
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    // Validates that the recipient cell number starts with '+' and is no more than 10 digits after/including length rules
    // Reuses the regex logic established in Part 1
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber != null && cellNumber.startsWith("+") && cellNumber.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Validates message length <= 250 characters
    public String validateMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Generates a random 10-digit Message ID
    public String generateMessageID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        this.messageID = sb.toString();
        return this.messageID;
    }

    // Creates the Message Hash: First 2 digits of ID : Message Number : First and Last words in ALL CAPS
    public String createMessageHash(String messageID, int messageNum, String message) {
        String firstTwoDigits = messageID.substring(0, 2);
        
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        
        this.messageHash = (firstTwoDigits + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
        return this.messageHash;
    }

    // Process user choice to Send, Disregard, or Store message
    public String sentMessage(int option) {
        switch (option) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return storeMessage();
            default:
                return "Invalid option selected.";
        }
    }

    /**
     * Stores the current message object formatted as a JSON object into a local 'messages.json' file.
     * Uses standard FileWriter with 'append = true' so new messages don't overwrite previous ones.
     * Reference:  https://www.w3schools.com/java/java_files_create.asp
     */
    public String storeMessage() {
        // 1. Manually format message properties into valid JSON structure[cite: 12]
        String jsonContent = "{\n" +
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\",\n" +
                "  \"recipient\": \"" + recipientCell + "\",\n" +
                "  \"message\": \"" + messageText + "\"\n" +
                "}\n";

        // 2. Open 'messages.json' and append the new JSON entry[cite: 13, 14]
        try (FileWriter fileWriter = new FileWriter("messages.json", true)) {
            fileWriter.write(jsonContent);
            return "Message Successfully Stored.";
        } catch (IOException e) {
            return "Failed to store message: " + e.getMessage();
        }
    }

    // Returns full formatted details of a message
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }

    // Getters and Setters
    public void setRecipientCell(String recipientCell) {
        this.recipientCell = recipientCell;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }
}

