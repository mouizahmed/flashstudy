package Models;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class UserList implements UserDatabase {
	private Map<String, User> users = new HashMap<>();
	private User currentUser;
	public String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public void addUser(String username, String email, String password, String confirmPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		User existingUserByUsername = getUser(username);
		User existingUserByEmail = getUserByEmail(email);
		
		
		if (existingUserByUsername == null && existingUserByEmail == null) {
			if (!password.equals(confirmPassword)) {
				
				throw new IllegalArgumentException("Passwords do not match");
			} else if (password.length() < 8) {
				throw new IllegalArgumentException("Password must be at least 8 characters");
			} else if (!Pattern.compile(regexPattern).matcher(email).matches()) {
				
				throw new IllegalArgumentException("Invalid email");
			}
			
			String cryptedPassword = encryptPassword(password);
			LocalDate currentDate = LocalDate.now();
			User user = new User(username, email, cryptedPassword, currentDate);
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
	public void login(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		User user = getUser(username);
		
		
		if (user == null) {
			throw new IllegalArgumentException("Username does not exist");
		} else if (!validatePassword(password, user.getPassword())) {
			throw new IllegalArgumentException("Incorrect password");
		}
		
		setCurrentUser(user);
		
	}
	
	@Override
	public void logout() {
		if (getCurrentUser() != null) {
			setCurrentUser(null);
		} else {
			throw new IllegalArgumentException("You're not logged in");
		}
		
	}
	
	private static byte[] getSalt() throws NoSuchAlgorithmException {
	    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	    byte[] salt = new byte[16];
	    sr.nextBytes(salt);
	    return salt;
	}
	
	private static String toHex(byte[] array) throws NoSuchAlgorithmException
	{
	    BigInteger bi = new BigInteger(1, array);
	    String hex = bi.toString(16);
	    
	    int paddingLength = (array.length * 2) - hex.length();
	    if(paddingLength > 0)
	    {
	        return String.format("%0"  +paddingLength + "d", 0) + hex;
	    }else{
	        return hex;
	    }
	}
	
	private static String encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();
		
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64*8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
				
	}
	
	private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		    
		String[] parts = storedPassword.split(":");
	    int iterations = Integer.parseInt(parts[0]);

	    byte[] salt = fromHex(parts[1]);
	    byte[] hash = fromHex(parts[2]);

	    PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), 
	        salt, iterations, hash.length * 8);
	    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    byte[] testHash = skf.generateSecret(spec).getEncoded();

	    int diff = hash.length ^ testHash.length;
	    for(int i = 0; i < hash.length && i < testHash.length; i++) {
	        diff |= hash[i] ^ testHash[i];
	    }
	    
	    return diff == 0;
		
	}
	
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
	    byte[] bytes = new byte[hex.length() / 2];

	    for(int i = 0; i < bytes.length ;i++) {
	        bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	    }
	    
	    return bytes;
	}
	
}
