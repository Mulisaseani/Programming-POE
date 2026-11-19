/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.progpoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */


public class MessageManagerTest {

    private MessageManager manager;

    @BeforeEach
    public void setUp() {
        manager = new MessageManager();

        // Test Data Message 1 (Sent)
        manager.addSentMessage("Did you get the cake?");

        // Test Data Message 2 (Stored)
        manager.addStoredMessage("Kyle bekker", "0011223344", "00:0:WHERETIME", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.");

        // Test Data Message 3 (Disregard)
        manager.addDisregardedMessage("Yohoooo, I am at your gate.");

        // Test Data Message 4 (Sent - Developer ID 0838884567)
        manager.addSentMessage("It is dinner time!");
        manager.addStoredMessage("Kyle bekker", "0838884567", "08:1:ITTIME", "0838884567",
                "It is dinner time!");

        // Test Data Message 5 (Stored)
        manager.addStoredMessage("Kyle bekker", "5566778899", "55:2:OKYOU", "+27838884567",
                "Ok, I am leaving without you.");
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertEquals("Did you get the cake?", manager.getSentMessages().get(0));
        assertEquals("It is dinner time!", manager.getSentMessages().get(1));
    }

    @Test
    public void testDisplayLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = manager.getLongestStoredMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchForMessageID() {
        String expected = "Recipient: 0838884567\nMessage: \"It is dinner time!\"";
        String actual = manager.searchByMessageID("0838884567");
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessagesForRecipient() {
        String expected = "\"Where are you? You are late! I have asked you to be on time.\" \"Ok, I am leaving without you.\"";
        String actual = manager.searchByRecipient("+27838884567");
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteMessageUsingHash() {
        String expected = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        String actual = manager.deleteMessageByHash("00:0:WHERETIME");
        assertEquals(expected, actual);
    }

    @Test
    public void testDisplayReport() {
        String report = manager.displayStoredMessagesReport();
        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Recipient:"));
        assertTrue(report.contains("Message:"));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
    }
}
