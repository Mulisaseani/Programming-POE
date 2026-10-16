/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.progpoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        // Runs before every test to ensure a clean instance
        message = new Message();
    }

    @Test
    public void testMessageLengthSuccess() {
        String testMsg = "Hi Mike, can you join us for dinner tonight?";
        String expected = "Message ready to send.";
        String actual = message.validateMessageLength(testMsg);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLengthFailure() {
        // Create string exceeding 250 characters
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("a");
        }
        String expected = "Message exceeds 250 characters by 10; please reduce the size.";
        String actual = message.validateMessageLength(longMsg.toString());
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientCellSuccess() {
        String testNum = "+27718693002";
        String expected = "Cell phone number successfully captured.";
        String actual = message.checkRecipientCell(testNum);
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientCellFailure() {
        String testNum = "08575975889";
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String actual = message.checkRecipientCell(testNum);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageHashGeneration() {
        // Using Test Case 1 details from assignment brief
        String messageID = "0012345678";
        int numSent = 0;
        String messageText = "Hi Mike, can you join us for dinner tonight?";
        
        String expectedHash = "00:0:HITONIGHT";
        String actualHash = message.createMessageHash(messageID, numSent, messageText);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testMessageIDLength() {
        String generatedID = message.generateMessageID();
        assertTrue(message.checkMessageID(generatedID));
    }

    @Test
    public void testSendMessageOptionSent() {
        String expected = "Message successfully sent.";
        String actual = message.sentMessage(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testSendMessageOptionDisregard() {
        String expected = "Press 0 to delete the message.";
        String actual = message.sentMessage(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testSendMessageOptionStore() {
        String expected = "Message successfully stored.";
        String actual = message.sentMessage(3);
        assertEquals(expected, actual);
    }
}
