package Models;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class StudyPlan {

	public String createdBy;
	public String studyPlanID;
	public String studyPlanTitle;
	public String testDate;
	public String frequency;
	public String difficulty;
	public String studyTime;
	public ArrayList<Deck> selectedDecks;
	public ArrayList<Deck> allStudyDecks;
	public ArrayList<Deck> allRepeatDecks;
	private Date dateCreated;
	private HashMap<Deck, Integer> deckRepeatCount;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private QuizSession quizSession;
	private User user;
	private JDBC mysql_database;

	public StudyPlan(String createdBy, String studyPlanID, String studyPlanTitle, String testDate, String frequency, String difficulty, 
			String studyTime, ArrayList<Deck> selectedDecks) {
		this.createdBy = createdBy;
		this.studyPlanID = studyPlanID;
		this.studyPlanTitle = studyPlanTitle;
		this.testDate = testDate;
		this.frequency = frequency;
		this.difficulty = difficulty;
		this.studyTime = studyTime;
		this.selectedDecks = selectedDecks;
		this.allStudyDecks = new ArrayList<>();
		this.allRepeatDecks = new ArrayList<>();
		this.deckRepeatCount = new HashMap<>(); // Initialize the deckRepeatCount HashMap
		try {
			this.mysql_database = new JDBC();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		// Loop through all selected decks
		for (Deck deck : selectedDecks) {
			// Check if the deck is already in AllStudyDecks or AllRepeatDecks
			if (!allStudyDecks.contains(deck) && !allRepeatDecks.contains(deck)) {
				// If the deck is not in AllStudyDecks and not in AllRepeatDecks, add it to AllStudyDecks
				allStudyDecks.add(deck);
				//System.out.print(allStudyDecks);
			}
			if(deck == null) {
				deckRepeatCount.put(null, 0);
				return;
			}
			deckRepeatCount.put(deck, deck.getCounter()); // Add the deck and its counter to the deckRepeatCount HashMap
		}
	}

	public void updateStudyDecks() {
		ArrayList<Deck> updatedAllRepeatDecks = new ArrayList<Deck>();
		for (Deck deck : allStudyDecks) {
			System.out.print("Counter: " + deck.getCounter());
			int count = deckRepeatCount.get(deck);
			if (deck.getCounter() == Integer.parseInt(frequency)) {
				Date latestQuizSessionDate = mysql_database.getLatestQuizSessionDate(deck, user);
				if (latestQuizSessionDate != null && quizSession.getAvgScore() == 1) {
					updatedAllRepeatDecks.add(deck);
					deck.setCounter(0);
					deckRepeatCount.put(deck, 0);
					continue;
				}
			} else if (deck.getCounter() < Integer.parseInt(frequency)) {
				deckRepeatCount.put(deck, count+1);
				continue;
			}
			updatedAllRepeatDecks.add(deck);
			deck.setCounter(0);
			deckRepeatCount.put(deck, 0);
		}

		for (Deck deck : updatedAllRepeatDecks) {
			Date latestQuizSessionDate = mysql_database.getLatestQuizSessionDate(deck, user);
			LocalDate latestQuizSessionLocalDate = latestQuizSessionDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate currentDate = LocalDate.now();
			long daysSinceStudied = ChronoUnit.DAYS.between(latestQuizSessionLocalDate, currentDate);
			if (daysSinceStudied >= getRepeatThreshold()) {
				allStudyDecks.add(deck);
				allRepeatDecks.remove(deck);
				continue;
			}
			//Date latestQuizSessionDate = mysql_database.getLatestQuizSessionDate(deck, user);
			if (latestQuizSessionDate != null) {
				long dateCreated = latestQuizSessionDate.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String formattedDate = formatter.format(dateCreated);
				System.out.println("Deck " + deck.getDeckTitle() + " was last studied on " + formattedDate);
			} else {
				System.out.println("Deck " + deck.getDeckTitle() + " has not been studied yet");
			}
		}
		allRepeatDecks = updatedAllRepeatDecks;
	}

	public int getRepeatThreshold() {
		int threshold = 0;
		switch (this.difficulty) {
		case "Easy":
			threshold = 7;
			break;
		case "Medium":
			threshold = 5;
			break;
		case "Hard":
			threshold = 3;
			break;
		}
		return threshold;
	}


	public ArrayList<Deck> getAllStudyDecks() {
		//System.out.print(selectedDecks);
		return selectedDecks;
	}

	public ArrayList<Deck> getAllRepeatDecks() {
		ArrayList<Deck> repeatDecks = new ArrayList<>();

		for (Deck deck : deckRepeatCount.keySet()) {
			if (deckRepeatCount.get(deck) > 0) {
				repeatDecks.add(deck);
			}
		}
		System.out.print(repeatDecks);
		return repeatDecks;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getStudyPlanTitle() {
		return this.studyPlanTitle;
	}

	public String getStudyPlanID() {
		return this.studyPlanID;
	}

	public ArrayList<Deck> getSelectedDecks() {
		return this.selectedDecks;
	}

	public void setSelectedDecks(ArrayList<Deck> selectedDecks) {
		this.selectedDecks = selectedDecks;
		
	}
}