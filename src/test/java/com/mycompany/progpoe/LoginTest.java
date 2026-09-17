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
public class LoginTest {
    
   private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
        login.setFirstName("Mulisa");
        login.setLastName("ll");
    }

    // --- USERNAME TESTS ---
    @Test
    public void testUsernameCorrectlyFormatted() {
        login.setUsername("mu_1");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        login.setUsername("mulisa!!!!!!!.");
        assertFalse(login.checkUserName());
    }

    // --- PASSWORD TESTS ---
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        login.setPassword("Mulisa@prog99!");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        login.setPassword("password");
        assertFalse(login.checkPasswordComplexity());
    }

    // --- CELL PHONE TESTS ---
    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        login.setCellPhoneNumber("+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        login.setCellPhoneNumber("08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    // --- LOGIN VERIFICATION TESTS ---
    @Test
    public void testLoginSuccessful() {
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    // --- MESSAGE STRING TESTS ---
    @Test
    public void testUsernameIncorrectMessage() {
        login.setUsername("kyle!!!!!!!.");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("+27838968976");
        
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    public void testPasswordIncorrectMessage() {
        login.setUsername("kyl_1");
        login.setPassword("password");
        login.setCellPhoneNumber("+27838968976");

        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    public void testCellPhoneIncorrectMessage() {
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("08966553");

        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        assertEquals(expected, login.registerUser());
    }
}
    

