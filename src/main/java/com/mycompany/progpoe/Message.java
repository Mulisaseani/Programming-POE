/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoe;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class Message {

    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;

    // Checks if message ID is no more than 10 characters[cite: 14]
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    // Checks strictly for +27 followed by 9 digits[cite: 14, 15]
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber != null && cellNumber.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Validates message length <= 250 characters[cite: 12, 15]
    public String validateMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Generates a random 10-digit Message ID using String manipulation[cite: 12, 23]
    public String generateMessageID() {
        Random random = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id = id + String.valueOf(random.nextInt(10));
        }
        this.messageID = id.substring(0, 10);
        return this.messageID;
    }

    // Creates Message Hash: First 2 digits of ID : Message Number : First & Last words in ALL CAPS[cite: 12, 14]
    public String createMessageHash(String messageID, int messageNum, String message) {
        String firstTwoDigits = messageID.substring(0, 2);

        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        this.messageHash = (firstTwoDigits + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
        return this.messageHash;
    }

    // Processes user action: 1 = Send, 2 = Disregard, 3 = Store[cite: 13, 14, 16]
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
     * Writes messages as a valid JSON array [ { ... }, { ... } ] in 'messages.json'[cite: 13, 14].
     * Reference / Attribution: https://www.w3schools.com/java/java_files_create.asp[cite: 24]
     */
    public String storeMessage() {
        String newJsonObject = "  {\n" +
                "    \"messageID\": \"" + messageID + "\",\n" +
                "    \"messageHash\": \"" + messageHash + "\",\n" +
                "    \"recipient\": \"" + recipientCell + "\",\n" +
                "    \"message\": \"" + messageText + "\"\n" +
                "  }";

        try {
            File file = new File("messages.json");
            String finalJsonArray;

            if (file.exists() && file.length() > 0) {
                String existing = Files.readString(file.toPath()).trim();
                if (existing.startsWith("[") && existing.endsWith("]")) {
                    String inner = existing.substring(1, existing.length() - 1).trim();
                    if (inner.isEmpty()) {
                        finalJsonArray = "[\n" + newJsonObject + "\n]";
                    } else {
                        finalJsonArray = "[\n" + inner + ",\n" + newJsonObject + "\n]";
                    }
                } else {
                    finalJsonArray = "[\n" + newJsonObject + "\n]";
                }
            } else {
                finalJsonArray = "[\n" + newJsonObject + "\n]";
            }

            try (FileWriter fileWriter = new FileWriter(file, false)) {
                fileWriter.write(finalJsonArray);
            }
            return "Message successfully stored.";
        } catch (IOException e) {
            return "Failed to store message: " + e.getMessage();
        }
    }

    // Returns formatted details of the message[cite: 13, 14]
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }

    // Getters and Setters
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getRecipientCell() {
        return recipientCell;
    }

    public void setRecipientCell(String recipientCell) {
        this.recipientCell = recipientCell;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }
}