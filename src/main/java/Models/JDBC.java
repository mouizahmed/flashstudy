package Models;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class JDBC {
	private static Connection conn;
	private static Statement stmt;
	private static Statement stmt2;
	private static ResultSet rs;
	private static ResultSet rs1;
	private static ResultSet rs2;
	private static String sql;
	private static String sql2;
	private static String query1;
	private PasswordUtilities passwordUtilities = new PasswordUtilities();

	public JDBC() {
		String url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_957a5ec054245a7?reconnect=true";
		String user = "b94e112144658f";
		String password = "7af4eb5c";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				//initialize();
				System.out.println("Connected to the database");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public User createNewUser(String username, String email, String password, String confirmPassword) {
		User user = null;
		
		try {
			LocalDate currentDate = LocalDate.now();
			String hashedPassword = PasswordUtilities.hashPassword(password);
			user = new User(username, email, hashedPassword, currentDate);
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO Users (username, email, password, regDate) VALUES ('" + user.getUsername() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + currentDate + "');");
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return user;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return user;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return user;
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			return user;
		}
	
	}
	
	
	public User verifyUser(String username, String password) {
		User user = null;
		try {
			stmt = conn.createStatement();
			sql = "SELECT * FROM Users WHERE username='" + username + "';";
			rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				if (PasswordUtilities.validate(password, rs1.getString("password"))) {
					user = new User(rs1.getString("username"), rs1.getString("email"), rs1.getString("password"), rs1.getDate("regDate").toLocalDate());
					return user;
				} else {
					throw new IllegalArgumentException("Incorrect Password");
				}
			} else {
				throw new SQLException("User does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return user;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return user;
		}
		
	}
	
	public Deck createDeck(String deckTitle, ArrayList<Flashcard> flashcards, boolean publicDeck, User currentUser, String deckID) {
		Deck deck = null;
		String addFlashcardsQuery = "INSERT INTO Flashcards (createdBy, flashcardID, deckID, question, answer) VALUES (?, ?, ?, ?, ?)";
		String addDeckQuery = "INSERT INTO Decks (createdBy, deckID, deckTitle, public) VALUES (?, ?, ?, ?);";
		try {
			
			PreparedStatement stmt1 = conn.prepareStatement(addDeckQuery);
			stmt1.setString(1, currentUser.getUsername());
			
			System.out.println(deckID);
			stmt1.setString(2, deckID);
			stmt1.setString(3, deckTitle);
			stmt1.setBoolean(4, publicDeck);
			System.out.println(stmt1);
			int rowsInserted1 = stmt1.executeUpdate();
			
			PreparedStatement stmt2 = conn.prepareStatement(addFlashcardsQuery);
			for (int i = 0; i < flashcards.size(); i++) {
				stmt2.setString(1, flashcards.get(i).createdBy);
				stmt2.setString(2, flashcards.get(i).flashcardID);
				stmt2.setString(3, flashcards.get(i).getDeckID());
				stmt2.setString(4, flashcards.get(i).getQuestion());
				stmt2.setString(5, flashcards.get(i).getAnswer());
				int rowsInserted2 = stmt2.executeUpdate();
			}
			
			deck = new Deck(deckTitle, flashcards, currentUser.getUsername(), publicDeck, deckID);
			return deck;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deck;
	}
	
	public ArrayList<Deck> publicDeckList() {
		ArrayList<Deck> publicDecks = new ArrayList<>();
		
		sql = "SELECT * FROM Decks WHERE public='1'";
		
				
		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			rs1 = stmt.executeQuery(sql);
			while (rs1.next()) {
				System.out.println(rs1.getString("deckTitle"));
				ArrayList<Flashcard> flashcards = new ArrayList<>();
				sql2 = "SELECT * FROM Flashcards WHERE deckID='" + rs1.getString("deckID") + "'";
				rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					flashcards.add(new Flashcard(rs2.getString("question"), rs2.getString("answer"), rs2.getString("createdBy"), rs2.getString("deckID")));
				}
				
				publicDecks.add(new Deck(rs1.getString("deckTitle"), flashcards, rs1.getString("createdBy"), rs1.getBoolean("public"), rs1.getString("deckID")));
			}
			
			return publicDecks;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return publicDecks;
		}
		
		
	}
	
	public ArrayList<Deck> searchPublicDeckQuery(String query) {
		ArrayList<Deck> searchedDecks = new ArrayList<>();
		
		sql = "SELECT * FROM Decks WHERE deckTitle LIKE '%" + query + "%' AND public='1'";
		
		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			rs1 = stmt.executeQuery(sql);
			
			while (rs1.next()) {
				ArrayList<Flashcard> flashcards = new ArrayList<>();
				sql2 = "SELECT * FROM Flashcards WHERE deckID='" + rs1.getString("deckID") + "'";
				rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					flashcards.add(new Flashcard(rs2.getString("question"), rs2.getString("answer"), rs2.getString("createdBy"), rs2.getString("deckID")));
				}
				
				searchedDecks.add(new Deck(rs1.getString("deckTitle"), flashcards, rs1.getString("createdBy"), rs1.getBoolean("public"), rs1.getString("deckID")));
			}
			
			return searchedDecks;
		} catch (SQLException e) {
			e.printStackTrace();
			return searchedDecks;
		}
	}
	

}
