//package Models;
//
//import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class UserListTest {
//	
//	private UserList userDatabase;
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		userDatabase = new UserList();
//	}
//
//	@Test
//	void testAddUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
//		
//		userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
//		
//		assertNotNull(userDatabase.getUser("mouizahmed"));
//		
//	}
//	
//	@Test
//	void testSearchExactUser() {
//		assertNull(userDatabase.getUser("mouizahmed"));
//	}
//	
//	@Test
//	void testAddUser_InvalidEmail() throws NoSuchAlgorithmException, InvalidKeySpecException {
//		
//		
//		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser("mouizahmed", "mouizahmed1", "12345678", "12345678"));
//		
//		
//	}
//	
//	@Test 
//	void testAddUser_PasswordsDontMatch() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		
//		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "123456789"));
//		
//	}
//	
//	@Test
//	void testAddUser_InvalidPasswordLength() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		
//		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "123456", "123456"));
//	}
//	
//	@Test
//	void testAddUser_DuplicateUsername() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		
//		userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
//		
//		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser("mouizahmed", "mouiza@my.yorku.ca", "12345678!", "12345678!"));
//	}
//	
//	@Test
//	void testAddUser_DuplicateEmail() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		
//		userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
//		
//		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser("mouiz", "mouizahmed1@gmail.com", "12345678", "12345678"));
//	}
//	
//	@Test 
//	void testLogin1() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
//		
//		userDatabase.login("mouizahmed", "12345678");
//		
//		User currentUser = userDatabase.getCurrentUser();
//		
//		assertEquals(userDatabase.getUser("mouizahmed"), currentUser);
//		
//
//	}
//	
//	@Test
//	void testLogin3() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
//		
//		assertThrows(IllegalArgumentException.class, ()-> userDatabase.login("mouizahmed123", "12345678"));
//	}
//	
//	@Test
//	void testLogin2() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
//		
//		assertThrows(IllegalArgumentException.class, ()-> userDatabase.login("mouizahmed", "12345678fsse"));
//	}
//	
//	@Test
//	void testLogOut() throws NoSuchAlgorithmException, InvalidKeySpecException {
//		userDatabase.addUser("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
//		
//		userDatabase.login("mouizahmed", "12345678");
//		userDatabase.logout();
//		assertEquals(userDatabase.getCurrentUser(), null);
//		
//	}
//
//}



