package Controller;



import Models.*;
import Views.*;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;

import java.awt.CardLayout;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;


import javax.swing.JFrame;
import javax.swing.JPanel;

import Models.AIModel;
import Models.Deck;
import Models.Flashcard;
import Models.JDBC;
import Models.Leaderboard;
import Models.QuizCreator;
import Models.QuizSession;
import Models.StudyPlan;
import Models.User;
import Models.Verdict;
import Views.BrowsePublicDeckPage;
import Views.CreateDeckPage;
import Views.CreateStudyPlanPage;
import Views.UserStudyPlannerPage;
import Views.EditDeck;
import Views.FactCheck;
import Views.LandingPage;

import Views.LeaderboardView;
import Views.LoginPage;
import Views.OpenDeckPage;
import Views.QuizPage;
import Views.QuizResults;
import Views.RegisterPage;
import Views.SessionPlayer;
import Views.UserView;


/**
 * Controller without MySQL connection
 * @author Mouiz_Ahmed
 *
 */
public class Controller {

//	private UserList userDatabase;
//	private DeckList deckDatabase;
	private JPanel main;
	private CardLayout card;
	private JDBC mysql_database;
	private User currentUser;
	private StudyPlan studyPlan;
	private static String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
          
	public Controller(JPanel main, CardLayout card) {
//		userDatabase = new UserList();
//		deckDatabase = new DeckList(userDatabase);
		this.main = main;
		this.card = card;

		
		try {
			this.mysql_database = new JDBC();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected JDBC getDatabase() {
		return this.mysql_database;
	}
	
	protected CardLayout getCard() {
		return this.card;
	}
	
	protected JPanel getMain() {
		return this.main;
	}


	public boolean createNewUser(String username, String email, String password, String confirmPassword) {

		if (!password.equals(confirmPassword)) {
			throw new IllegalArgumentException("Passwords do not match");
		} else if (password.length() < 8) {
			throw new IllegalArgumentException("Password must be at least 8 characters");
		} else if (!Pattern.compile(regexPattern).matcher(email).matches()) {
			throw new IllegalArgumentException("Invalid email");
		} else if (username.length() < 5) {
			throw new IllegalArgumentException("Username must be at least 5 characters");
		} else {
			try {
				currentUser = this.mysql_database.createNewUser(username, email, password, confirmPassword);
				if (currentUser != null) {
					LandingPage landingPage = new LandingPage(this);
					main.add(landingPage, "landingPage");
					card.show(main, "landingPage");
					return true;
				} else {
					return false;
				}
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}


	}

	public boolean login(String username, String password) {
		try {
			currentUser = this.mysql_database.verifyUser(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			return false;
		}
		if (currentUser == null) {
			return false;
		} else {
			LandingPage landingPage = new LandingPage(this);
			main.add(landingPage, "landingPage");
			card.show(main, "landingPage");
			return true;
		}
	}

	public void logout() {
		currentUser = null;
		this.welcomePage();
	}

	public void previous() {
		card.previous(main);
	}

	public void welcomePage() {
		System.out.println("WELCOME!");
		card.show(main, "welcomePage");
	}

	public void loginPage() {
		System.out.println("LOGIN!");
		card.show(main, "loginPage");
	}

	public void registerPage() {
		System.out.println("REGISTER!");
		card.show(main, "registerPage");
	}

	private boolean hasBeenCalled = false; 													//For tracking Spam Message

	public void landingPage() {
		System.out.println("LANDINGPAGE!");

		card.show(main, "landingPage");

		//Send Spam Email and ensure not to send again and again
		EmailMessageMaker emailMessageMaker = new EmailMessageMaker();
		if(!hasBeenCalled) {
			emailMessageMaker.sendSpamEmail(currentUser.getEmail(), currentUser.getUsername());
			hasBeenCalled = true;
			System.out.println("Spam sent and Counter set for 10 Days");
		}else {
			System.out.println("Spam Email Already Sent");
		}
	}

	public void createDeckPage() {
		String deckID = UUID.randomUUID().toString();
		System.out.println("CREATE NEW DECK!");
		CreateDeckPage createDeckPage = new CreateDeckPage(this, deckID);
		System.out.println(main.getComponentCount());
		main.add(createDeckPage, "createDeckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "createDeckPage" + num);
	}

	public void browse() {
		System.out.println("Browse");
		BrowsePublicDeckPage browsePublicDeckPage = new BrowsePublicDeckPage(this);
		main.add(browsePublicDeckPage, "browsePublicDeckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "browsePublicDeckPage" + num);
		
	}

	public Flashcard createFlashcard(String question, String answer, String deckID) {
		Flashcard flashcard = new Flashcard(question, answer, currentUser.getUsername(), deckID);
		return flashcard;
	}
	

	public Flashcard createFlashcard(String question, String answer, String deckID, byte[] flashCardImgData) {
		Flashcard flashcard = new Flashcard(question, answer, currentUser.getUsername(), deckID,flashCardImgData);
		return flashcard;
	}

	public void createDeck(String deckTitle, ArrayList<Flashcard> flashcards, boolean publicDeck, String deckID, String schoolName, String facultyName, String courseName, String description) {
		Deck deck = null;
		try {
			deck = mysql_database.createDeck(deckTitle, flashcards, publicDeck, currentUser, deckID, schoolName, facultyName, description, courseName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getCurrentUser().getUserDeckList().add(deck);

		deckPage(deck);

		//send email that new deck is created
		EmailMessageMaker emailMessageMaker = new EmailMessageMaker();
		emailMessageMaker.sendNewDeckEmail(currentUser.getEmail(), currentUser.getUsername(),deck.getDeckTitle());
	}

	public ArrayList<Deck> searchPublicDecks(String deckTitle) {
		return mysql_database.searchPublicDeckQuery(deckTitle);
	}

	
	public ArrayList<Deck> searchUserDecks(String deckTitle) {
		return mysql_database.searchUserDeckQuery(deckTitle, this.getCurrentUser().getUsername());
	}
	

	public ArrayList<Deck> allPublicDecks() {
		ArrayList<Deck> publicDecks = mysql_database.publicDeckList();
		System.out.println(publicDecks.size());
		return publicDecks;
	}
  
  public ArrayList<Deck> allUserDecks() {
		ArrayList<Deck> userDecks = mysql_database.userDeckList();
		System.out.println(userDecks.size());
		return userDecks;
	}
	
	public void deckPage(Deck deck) {
		OpenDeckPage deckPage = new OpenDeckPage(deck, this);
		main.add(deckPage, "deckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "deckPage" + num);
	}

	public void session(ArrayList<Flashcard> flashcards) {
		SessionPlayer sessionPage = new SessionPlayer(flashcards, this);
		main.add(sessionPage, "sessionPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "sessionPage" + num);
	}

	public void quiz(Deck deck) {
		QuizCreator quizCreator = new QuizCreator(deck);
		QuizSession quizSession = new QuizSession(quizCreator, currentUser);
		QuizPage quizPage = new QuizPage(quizSession, this);
		main.add(quizPage, "quizPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "quizPage" + num);
	}

	public void quizResults(QuizSession quizSession) {
		QuizResults quizResultsPage = new QuizResults(quizSession, this);
		this.mysql_database.createQuiz(quizSession);
		main.add(quizResultsPage, "quizResultsPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "quizResultsPage" + num);
		System.out.println("SCORE" + quizSession.getScore());
		System.out.println("TIME TAKEN" + quizSession.getdurationInMins());
	}

	public void profilePage(User user) {
		UserView profilePage = new UserView(user, this);
		main.add(profilePage, "profilePage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "profilePage" + num);
	}

	public User getCurrentUser() {
		return this.currentUser;
	}
  
  	public void createStudyPlanPage(User user, ArrayList<Deck> decks) {
		CreateStudyPlanPage createStudyPlanPage = new CreateStudyPlanPage(user, decks, this); //added
		main.add(createStudyPlanPage, "createStudyPlanPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "createStudyPlanPage" + num);
	}
	
	public void UserStudyPlannerPage(User user, StudyPlan studyPlan) {
		ArrayList<StudyPlan> retrievedStudyPlan = null;//this.mysql_database.getAllStudyPlansForUser(user.getUsername());
		UserStudyPlannerPage userStudyPlannerPage = new UserStudyPlannerPage(user, retrievedStudyPlan, this); //added
		main.add(userStudyPlannerPage, "userStudyPlannerPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "userStudyPlannerPage" + num);
	}

	public void addDeckToProfile(Deck deck) {
		Deck deckCopy = null;
		try {
			deckCopy = this.mysql_database.addDeckToProfile(deck, this.currentUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.currentUser.addDeck(deckCopy);
	}

	public void updateFlashcardDifficulty(Flashcard flashcard, String colour) {
		this.mysql_database.setFlashcardDifficulty(flashcard, colour);
	}

	public void leaderboardPage(Deck deck) {
		Leaderboard leaderboard = this.mysql_database.getQuizLeaderboard(deck);
		LeaderboardView leaderboardPage = new LeaderboardView(leaderboard, this);
		main.add(leaderboardPage, "leaderboardPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "leaderboardPage" + num);
	}

	public void emailPage(){
		System.out.println("Email Reminder View");
		ExamReminderView examReminderView = new ExamReminderView();
	}


	public void verifyFlashcard(Deck deck, Flashcard flashcard) throws IOException, InterruptedException {
		AIModel verification = new AIModel();
		
		Verdict verdict = verification.verifyFlashcard(flashcard.getQuestion(), flashcard.getAnswer());
		
		JFrame factCheck = new FactCheck(deck, flashcard, this, verdict);
		
		factCheck.setVisible(true);
	}
	
	public void updateFlashcard(Deck deck, Flashcard flashcard, String updatedDefinition) {
		Deck updatedDeck = this.mysql_database.updateDefinition(deck, flashcard, updatedDefinition);
		
		this.deckPage(updatedDeck);
	}
	
	public String autoComplete(String term, String context) {
		AIModel autoCompletion = new AIModel();
		
		String aiTerm = "";
		try {
			aiTerm = autoCompletion.autoComplete(term, context);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aiTerm;
	}
	
	public void editDeckPage(Deck deck) {
		
		EditDeck editDeckPage = new EditDeck(this, deck);
		main.add(editDeckPage, "editDeckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "editDeckPage" + num);
	}
	
	public void updateFlashcard(Flashcard flashcard) {
		this.mysql_database.updateFlashcard(flashcard);
	}
	
	public void deleteFlashcard(Flashcard flashcard) {
		this.mysql_database.deleteFlashcard(flashcard);
	}
	
	public void addFlashcard(Flashcard flashcard, String deckID) {
		this.mysql_database.addFlashcard(flashcard, deckID, this.getCurrentUser().getUsername());
	}
	
	public void updateDeckInfo(Deck deck) {
		this.mysql_database.updateDeckInfo(deck);
		this.deckPage(deck);
	}
	
}
