package Models;

import java.util.ArrayList;
import java.util.Map;

public interface UserDatabase {
	void addUser(User user);
	User getUser(String username);
	void login(String username, String password);
	User getCurrentUser();
	void setCurrentUser(User currentUser);
	ArrayList<User> getAllUsers();
}
