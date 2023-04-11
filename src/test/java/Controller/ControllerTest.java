package Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.CardLayout;
import java.awt.Component;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flashstudy.Main;

class ControllerTest {
	
	private static Main main;
	private static Controller controller;
	
	@BeforeAll
	static void init() {
        main = new Main();
        controller = main.getController();
        controller.getDatabase().setAutoCommit(false);
        
        
    }

	@Test
    void testCreateNewUser() {
        assertTrue(controller.createNewUser("test12", "test12@test.com", "password", "password"));
    }
	
	@Test
    void testCreateNewUserWithShortUsername() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.createNewUser("user", "testuser@test.com", "password", "password");
        });
        assertEquals("Username must be at least 5 characters", exception.getMessage());
    }
	
	@Test
    void testCreateNewUserWithPasswordsNotMatching() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.createNewUser("testuser", "testuser@test.com", "password", "mismatchedpassword");
        });
        assertEquals("Passwords do not match", exception.getMessage());
    }
	
	@Test
    void testCreateNewUserWithShortPassword() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.createNewUser("testuser", "testuser@test.com", "short", "short");
        });
        assertEquals("Password must be at least 8 characters", exception.getMessage());
    }
	
	@Test
    void testLogin() throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        controller.createNewUser("testuser123", "testuser123@test.com", "password", "password");
        assertTrue(controller.login("testuser123", "password"));
    }
	
	@Test
    void testLoginWithInvalidCredentials() throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        controller.createNewUser("testuser1234", "testuser1234@test.com", "password", "password");
        assertThrows(IllegalArgumentException.class, () -> {
        	controller.login("testuser", "wrongpassword");
        });
    }
	
	@Test
    void testLoginWithInvalidCredentials2() throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        controller.createNewUser("testuser12345", "testuser12345@test.com", "password", "password");
        
        assertFalse(controller.login("wronguser", "password"));
       
    }
	
	@Test
    void testLogout() throws SQLException {
		controller.createNewUser("testuser20", "testuser20@test.com", "password", "password");
		controller.login("testuser20", "password");
        controller.logout();
        assertNull(controller.getCurrentUser());
    }

}
