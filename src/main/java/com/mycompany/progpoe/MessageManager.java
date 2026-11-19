/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoe;

/**
 *
 * @author Student
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MessageManager {

    // Five required arrays/lists from Part 3 specification
    private ArrayList<String> sentMessages = new ArrayList<>();
    private ArrayList<String> disregardedMessages = new ArrayList<>();
    private ArrayList<String> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();

    // Parallel lists to track recipients and senders for reports and searches
    private ArrayList<String> storedRecipients = new ArrayList<>();
    private ArrayList<String> storedSenders = new ArrayList<>();

    // Add a sent message
    public void addSentMessage(String message) {
        sentMessages.add(message);
    }

    // Add a disregarded message
    public void addDisregardedMessage(String message) {
        disregardedMessages.add(message);
    }

    // Add a stored message along with its parallel array details
    public void addStoredMessage(String sender, String id, String hash, String recipient, String message) {
        storedSenders.add(sender);
        messageIDs.add(id);
        messageHashes.add(hash);
        storedRecipients.add(recipient);
        storedMessages.add(message);
    }

    /**
     * Reads stored JSON objects from 'messages.json' into the parallel arrays.
     * Reference / Attribution: https://www.w3schools.com/java/java_files_read.asp
     */
    public void loadStoredMessagesFromJSON(String defaultSender) {
        File file = new File("messages.json");
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String id = "", hash = "", recipient = "", text = "";

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"messageID\":")) {
                    id = extractJsonValue(line);
                } else if (line.startsWith("\"messageHash\":")) {
                    hash = extractJsonValue(line);
                } else if (line.startsWith("\"recipient\":")) {
                    recipient = extractJsonValue(line);
                } else if (line.startsWith("\"message\":")) {
                    text = extractJsonValue(line);
                } else if (line.equals("}")) {
                    if (!id.isEmpty() && !messageIDs.contains(id)) {
                        addStoredMessage(defaultSender, id, hash, recipient, text);
                    }
                    id = "";
                    hash = "";
                    recipient = "";
                    text = "";
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading messages.json: " + e.getMessage());
        }
    }

    // Helper method to clean quotes and commas from JSON lines
    private String extractJsonValue(String line) {
        int colonIndex = line.indexOf(":");
        String value = line.substring(colonIndex + 1).trim();
        if (value.endsWith(",")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        return value;
    }

    // Option 4a: Display the sender and recipient of all stored messages
    public String displaySenderAndRecipient() {
        if (storedMessages.isEmpty()) {
            return "No stored messages available.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < storedMessages.size(); i++) {
            sb.append("Sender: ").append(storedSenders.get(i))
              .append(" | Recipient: ").append(storedRecipients.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    // Option 4b: Display the longest stored message
    public String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages available.";
        }
        String longest = storedMessages.get(0);
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    // Option 4c: Search for a message ID and display the corresponding recipient and message
    public String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "Recipient: " + storedRecipients.get(i) + "\nMessage: \"" + storedMessages.get(i) + "\"";
            }
        }
        return "Message ID not found.";
    }

    // Option 4d: Search for all messages stored for a particular recipient
    public String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < storedRecipients.size(); i++) {
            if (storedRecipients.get(i).equals(recipient)) {
                if (found) {
                    sb.append(" ");
                }
                sb.append("\"").append(storedMessages.get(i)).append("\"");
                found = true;
            }
        }
        if (!found) {
            return "No messages found for recipient: " + recipient;
        }
        return sb.toString();
    }

    // Option 4e: Delete a message using the message hash
    public String deleteMessageByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                String deletedMessage = storedMessages.get(i);

                storedSenders.remove(i);
                messageIDs.remove(i);
                messageHashes.remove(i);
                storedRecipients.remove(i);
                storedMessages.remove(i);

                return "Message: \"" + deletedMessage + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // Option 4f: Display a report that lists the full details of all stored messages
    public String displayStoredMessagesReport() {
        if (storedMessages.isEmpty()) {
            return "No stored messages to display in report.";
        }
        StringBuilder report = new StringBuilder();
        report.append("=========================================\n");
        report.append("           STORED MESSAGES REPORT        \n");
        report.append("=========================================\n");
        for (int i = 0; i < storedMessages.size(); i++) {
            report.append("Sender:       ").append(storedSenders.get(i)).append("\n");
            report.append("Message ID:   ").append(messageIDs.get(i)).append("\n");
            report.append("Message Hash: ").append(messageHashes.get(i)).append("\n");
            report.append("Recipient:    ").append(storedRecipients.get(i)).append("\n");
            report.append("Message:      \"").append(storedMessages.get(i)).append("\"\n");
            report.append("-----------------------------------------\n");
        }
        return report.toString();
    }

    // Getters for arrays (used in unit tests)
    public ArrayList<String> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<String> getDisregardedMessages() {
        return disregardedMessages;
    }

    public ArrayList<String> getStoredMessages() {
        return storedMessages;
    }

    public ArrayList<String> getMessageHashes() {
        return messageHashes;
    }

    public ArrayList<String> getMessageIDs() {
        return messageIDs;
    }
}

