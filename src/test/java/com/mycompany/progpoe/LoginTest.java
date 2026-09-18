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
        login.setFirstName("Kyle");
        login.setLastName("bekker");
    }

    // ==========================================
    // 1. assertEquals Unit Tests
    // ==========================================

    @Test
    public void testUsernameCorrectlyFormattedMessage() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginSuccess = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        
        String expected = "Welcome Kyle ,bekker it is great to see you.";
        String actual = login.returnLoginStatus(loginSuccess);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameIncorrectlyFormattedMessage() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordMeetsComplexityMessage() {
        String expected = "User registered successfully.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordDoesNotMeetComplexityMessage() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser("kyl_1", "password", "+27838968976");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testCellPhoneCorrectlyFormattedMessage() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedMessage() {
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        
        assertEquals(expected, actual);
    }

    // ==========================================
    // 2. assertTrue / assertFalse Unit Tests
    // ==========================================

    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
    

