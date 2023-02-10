package Models;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserListTest {
	
	private UserList userDatabase;
	
	@BeforeEach
	void setUp() throws Exception {
		userDatabase = new UserList();
	}

	@Test
	void testAddUser() {
		User user = new User("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
		userDatabase.addUser(user);
		
		User actual = userDatabase.getUser("mouizahmed");
		assertEquals(user, actual);
		
	}
	
	@Test
	void testAddUser_InvalidEmail() {
		User user = new User("mouizahmed", "mouizahmed1", "12345678", "12345678");
		
		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser(user));
		
		
	}
	
	@Test 
	void testAddUser_PasswordsDontMatch() {
		User user = new User("mouizahmed", "mouizahmed1@gmail.com", "12345678", "123456789");
		
		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser(user));
		
	}
	
	@Test
	void testAddUser_InvalidPasswordLength() {
		User user = new User("mouizahmed", "mouizahmed1@gmail.com", "123456", "123456");
		
		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser(user));
	}
	
	@Test
	void testAddUser_DuplicateUsername() {
		User user1 = new User("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
		User user2 = new User("mouizahmed", "mouiza@my.yorku.ca", "12345678!", "12345678!");
		
		userDatabase.addUser(user1);
		
		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser(user2));
	}
	
	@Test
	void testAddUser_DuplicateEmail() {
		User user1 = new User("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
		User user2 = new User("mouiz", "mouizahmed1@gmail.com", "12345678", "12345678");
		
		userDatabase.addUser(user1);
		
		assertThrows(IllegalArgumentException.class, ()-> userDatabase.addUser(user2));
	}
	
	@Test 
	void testLogin1() {
		User user1 = new User("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
		userDatabase.addUser(user1);
		
		userDatabase.login("mouizahmed", "12345678");
		
		User currentUser = userDatabase.getCurrentUser();
		
		assertEquals(user1, currentUser);
		
		//System.out.println(getUser.getPassword());
	}
	
	@Test
	void testLogin2() {
		User user1 = new User("mouizahmed", "mouizahmed1@gmail.com", "12345678", "12345678");
		userDatabase.addUser(user1);
		
		//userDatabase.login("mouizahmed", "12345678fsse");
		
		
		assertThrows(IllegalArgumentException.class, ()-> userDatabase.login("mouizahmed", "12345678fsse"));
	}

}
