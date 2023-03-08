package Models;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Map;

public interface UserDatabase {
	User addUser(String username, String email, String password, String confirmPassword) throws NoSuchAlgorithmException, InvalidKeySpecException;
	User getUser(String username);
	void login(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	void logout();
	User getCurrentUser();
	void setCurrentUser(User currentUser);
	ArrayList<User> getAllUsers();
}
