package Controller;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import Models.Deck;
import Models.Flashcard;

/**
 * Controller without MySQL connection
 * @author Mouiz_Ahmed
 *
 */
public class Controller {
	
	
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
		
	
	public static boolean createNewUser(String username, String email, String password, String confirmPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		boolean created = false;
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		
		boolean emailValidation = Pattern.compile(regexPattern).matcher(email).matches();
		
		try {
		
			if (username.length() <= 1) {
				throw new IllegalArgumentException("Please enter a valid username");
			} else if (email.contains(" ") || emailValidation == false) {
				throw new IllegalArgumentException("Please enter a valid email address");
			} else if (password.length() < 8) {
				throw new IllegalArgumentException("Please enter a password that has 8 or more characters!");
			} else if (!password.equals(confirmPassword)) {
				throw new IllegalArgumentException("Passwords do not match.");
			}
			
			String cryptedPassword = encryptPassword(password);
	
		
			LocalDate currentDate = LocalDate.now();
	

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		return created;
	}
	
	
public static ArrayList<Deck> getAllDecks() {
		
		ArrayList<Deck> allDecks = new ArrayList<>();

		
		
		return allDecks;
		
	}
	
	
	public static ArrayList<Deck> getUserDecks(String username) {
		
		ArrayList<Deck> userDecks = new ArrayList<>();
		
		return userDecks;
		
	}
	

	
	public static boolean login(String username, String password) {
		
		boolean check = false;
		String cryptedPassword = "";
		
		try {
			
			boolean exists = false;
			boolean matched = validatePassword(password, cryptedPassword);
			
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return check;
		
	}
	
	
}
