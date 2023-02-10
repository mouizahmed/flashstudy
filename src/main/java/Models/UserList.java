package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserList implements UserDatabase {
	private Map<String, User> users = new HashMap<>();
	private User currentUser;
	public String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		User existingUserByUsername = getUser(user.getUsername());
		User existingUserByEmail = getUserByEmail(user.getEmail());
		
		
		if (existingUserByUsername == null && existingUserByEmail == null) {
			if (!user.confirmPassword()) {
				
				throw new IllegalArgumentException("Passwords do not match");
			} else if (!user.passwordLength()) {
				throw new IllegalArgumentException("Password must be at least 8 characters");
			} else if (!Pattern.compile(regexPattern).matcher(user.getEmail()).matches()) {
				
				throw new IllegalArgumentException("Invalid email");
			}
			users.put(user.getUsername(), user);
		} else if (existingUserByUsername != null){
			throw new IllegalArgumentException("Username already exists");
		} else if (existingUserByEmail != null) {
			throw new IllegalArgumentException("Email already exists");
		}
		
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return users.get(username);
	}
	
	public User getUserByEmail(String email) {
		for (User user : users.values()) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		
		return null;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		// TODO Auto-generated method stub
		return new ArrayList<>(users.values());
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}

	@Override
	public void setCurrentUser(User currentUser) {
		// TODO Auto-generated method stub
		this.currentUser = currentUser;
	}

	@Override
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		User user = getUser(username);
		if (user == null) {
			throw new IllegalArgumentException("Username does not exist");
		} else if (!user.checkPassword(password)) {
			throw new IllegalArgumentException("Incorrect password");
		}
		
		setCurrentUser(user);
		
	}
	
}
