package Controller;



import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import Models.Deck;
import Models.Flashcard;

public class MiddleTier {

	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static ResultSet rs1;
	private static String sql;
	private static int ID;
	
	public static String user;
	
	// MySQL Connection
	
	public MiddleTier() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/flashstudy", "root", "password");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flashstudy", "root", "admin");
			if (conn != null) {
				initialize();
				System.out.println("Connected to Database");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		String result = "";
		try {
			stmt = conn.createStatement();
			sql = "SELECT * FROM Users";
			rs1 = stmt.executeQuery(sql);
			while (rs1.next()) {
				result += rs1.getString("username");
				result += " ";
			}
			
			//System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
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
		
	
	public static boolean createNewUser(String username, String email, String password, String confirmPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		boolean created = false;
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		
		boolean emailValidation = Pattern.compile(regexPattern).matcher(email).matches();
		
		try {
			//System.out.println(username);
			//System.out.println(username.length());
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
	
			try {
				stmt = conn.createStatement();
				sql = "INSERT INTO Users (username, email, password, regDate) VALUES ('" + username + "', '" + email + "', '" + cryptedPassword + "', '" + currentDate + "') \n";
				stmt.execute(sql);
				created = true;
			} catch (SQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				System.out.println("Invalid input");
			} catch (Exception e) {
				System.out.println("Error");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		return created;
	}
	
	
public static ArrayList<Deck> getAllDecks() {
		
		ArrayList<Deck> userDecks = new ArrayList<>();
	
		try {
			//stmt = conn.createStatement();
			sql = "SELECT * from Decks";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				//System.out.println("h");
				ArrayList<Flashcard> userFlashcards = new ArrayList<>();
				//Deck deck = new Deck(rs.getInt("id"), rs.getString("deckTitle"), userFlashcards, rs.getString("createdBy"));
				stmt = conn.createStatement();
				sql = "SELECT * from Flashcards";
				rs1 = stmt.executeQuery(sql);
				
				while(rs1.next()) {
					//Flashcard flashcard = new Flashcard(rs1.getString("question"), rs1.getString("answer"), rs1.getInt("id"), rs1.getInt("deckID"), rs1.getString("createdBy"));
//					if (flashcard.deckID == deck.deckID) {
//						deck.addFlashcard(flashcard);
//					}
				}
				
				//userDecks.add(deck);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return userDecks;
		
	}

	public static ArrayList<Flashcard> getOtherFlashCards(String createdBy, int deckID){
		ArrayList<Flashcard> otherFlashCards = new ArrayList<>();
		sql = "SELECT question, answer FROM flashcards WHERE createdBy = ? AND deckID = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, createdBy);
			stmt.setInt(2, deckID);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String question = rs.getString("question");
					String answer = rs.getString("answer");
					Flashcard flashcard = new Flashcard(question, answer);
					otherFlashCards.add(flashcard);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return otherFlashCards;
	}
	
	public static ArrayList<Deck> getUserDecks(String username) {
		
		ArrayList<Deck> userDecks = new ArrayList<>();
		//System.out.println(username);
		try {
			//stmt = conn.createStatement();
			sql = "SELECT * from Decks WHERE createdBy='" + username + "'";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				//System.out.println("h");
				ArrayList<Flashcard> userFlashcards = new ArrayList<>();
				//Deck deck = new Deck(rs.getInt("id"), rs.getString("deckTitle"), userFlashcards, rs.getString("createdBy"));
				stmt = conn.createStatement();
				sql = "SELECT * from Flashcards WHERE createdBy='" + username + "'";
				rs1 = stmt.executeQuery(sql);
				
				while(rs1.next()) {
					//Flashcard flashcard = new Flashcard(rs1.getString("question"), rs1.getString("answer"), rs1.getInt("id"), rs1.getInt("deckID"), rs1.getString("createdBy"));
//					if (flashcard.deckID == deck.deckID) {
//						deck.addFlashcard(flashcard);
//					}
				}
				
				//userDecks.add(deck);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return userDecks;
		
	}
	
	
	public static String user() {
		return user;
	}
	
	public static boolean login(String username, String password) {
		
		boolean check = false;
		String cryptedPassword = "";

		try {
			stmt = conn.createStatement();
			sql = "SELECT * from Users WHERE username='" + username + "'"; 
			rs = stmt.executeQuery(sql);
			boolean exists = false;
			while (rs.next()) {
				cryptedPassword = rs.getString("password");
				exists = true;
			}
		
			
			if (!exists) {
				throw new Exception("No user found");
			}
			
			boolean matched = validatePassword(password, cryptedPassword);
		    //System.out.println(matched);
		    
		    if (matched) {
		    	check = true;
		    	System.out.println("Correct Password");
		    	user = username;
		    } else {
		    	throw new Exception("Incorrect Password");
		    }
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return check;
		
	}
	
	
	
}
