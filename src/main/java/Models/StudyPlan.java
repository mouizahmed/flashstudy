package Models;

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
    public int studyTimeDays;
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
    		String studyTime, int studyTimeDays, ArrayList<Deck> selectedDecks) {
        this.createdBy = createdBy;
        this.studyPlanID = studyPlanID;
        this.studyPlanTitle = studyPlanTitle;
    	this.testDate = testDate;
        this.frequency = frequency;
        this.difficulty = difficulty;
        this.studyTime = studyTime;
        this.studyTimeDays = studyTimeDays;
        this.selectedDecks = selectedDecks;
        //this.dateCreated = dateCreated;
        this.allStudyDecks = new ArrayList<>();
        this.allRepeatDecks = new ArrayList<>();
        this.deckRepeatCount = new HashMap<>(); // Initialize the deckRepeatCount HashMap
        this.mysql_database = new JDBC();

        // Loop through all selected decks
        for (Deck deck : selectedDecks) {
            // Check if the deck is already in AllStudyDecks or AllRepeatDecks
            if (!allStudyDecks.contains(deck) && !allRepeatDecks.contains(deck)) {
                // If the deck is not in AllStudyDecks and not in AllRepeatDecks, add it to AllStudyDecks
                allStudyDecks.add(deck);
                //System.out.print(allStudyDecks);
            }
            deckRepeatCount.put(deck, deck.getCounter()); // Add the deck and its counter to the deckRepeatCount HashMap
        }
    }
    
        public void updateStudyDecks() {
            ArrayList<Deck> allRepeatDecks = new ArrayList<Deck>();
            for (Deck deck : allStudyDecks) {
                if (deck.getCounter() == Integer.parseInt(frequency)) {
                    Date latestQuizSessionDate = mysql_database.getLatestQuizSessionDate(deck, user);
                    if (latestQuizSessionDate != null && quizSession.getAvgScore() == 1) {
                        allRepeatDecks.add(deck);
                        deck.setCounter(0);
                        continue;
                    }
                } else if (deck.getCounter() < Integer.parseInt(frequency)) {
                    continue;
                }
                allRepeatDecks.add(deck);
                deck.setCounter(0);
            }

            for (Deck deck : allRepeatDecks) {
            	Date latestQuizSessionDate = mysql_database.getLatestQuizSessionDate(deck, user);
                LocalDate latestQuizSessionLocalDate = latestQuizSessionDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate currentDate = LocalDate.now();
                long daysSinceStudied = ChronoUnit.DAYS.between(latestQuizSessionLocalDate, currentDate);
                if (daysSinceStudied >= getRepeatThreshold()) {
                    allStudyDecks.add(deck);
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
        	System.out.print(selectedDecks);
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
}

        
       // Loop through all decks in AllRepeatDecks
//        for (Deck deck : allRepeatDecks) {
//        //	lastQuiztaken = quizSession.get
//            int daysSinceStudied = calculateDaysSinceLastStudied(deck, currentDate);
//         // Check if the number of days since the deck was last studied is equal to or greater than the repeat threshold
//            if (daysSinceStudied >= deck.getRepeatThreshold()) {
//                // Add the deck back to allStudyDecks
//                allStudyDecks.add(deck);
//                // Remove the deck from allRepeatDecks
//                allRepeatDecks.remove(deck);
//            }
       // }
 //   }

//        public void createStudyPlan() {
//            // Calculate the total number of flashcards in all the selected decks
//            int totalFlashcards = 0;
//            for (Deck deck : selectedDecks) {
//                totalFlashcards += deck.getNumFlashcards();
//            }
//            
//            // Calculate the number of flashcards the user needs to study per day to finish all the decks before the test date
//            int daysToTest = (int) ((testDate.getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24));
//            int flashcardsPerDay = (int) Math.ceil((double) totalFlashcards / (double) daysToTest);
//            
//            // Create a list of flashcards to study each day
//            ArrayList<Flashcard> studyList = new ArrayList<Flashcard>();
//            for (Deck deck : selectedDecks) {
//                int repeatCount = deckRepeatCount.getOrDefault(deck, 0);
//                for (int i = 0; i < deck.getNumFlashcards(); i++) {
//                    Flashcard flashcard = deck.getFlashcard(i);
//                    if (!studyList.contains(flashcard)) {
//                        // Check if the flashcard needs to be repeated based on the adaptive learning algorithm
//                        int daysSinceLastStudied = flashcard.getDaysSinceLastStudied();
//                        double successRate = flashcard.getSuccessRate();
//                        int repeatThreshold = calculateRepeatThreshold(daysSinceLastStudied, successRate, repeatCount);
//                        if (repeatCount < selectedDecks.size() && daysSinceLastStudied >= repeatThreshold) {
//                            studyList.add(flashcard);
//                            deckRepeatCount.put(deck, repeatCount + 1);
//                        }
//                    }
//                }
//            }
//            
//            // Divide the study list into days
//            int studyListSize = studyList.size();
//            int days = (int) Math.ceil((double) studyListSize / (double) flashcardsPerDay);
//            ArrayList<ArrayList<Flashcard>> studyDays = new ArrayList<ArrayList<Flashcard>>();
//            for (int i = 0; i < days; i++) {
//                int startIndex = i * flashcardsPerDay;
//                int endIndex = Math.min(startIndex + flashcardsPerDay, studyListSize);
//                ArrayList<Flashcard> studyDay = new ArrayList<Flashcard>(studyList.subList(startIndex, endIndex));
//                studyDays.add(studyDay);
//            }
//            
//            // Create a user study planner page for each day
//            for (int i = 0; i < studyDays.size(); i++) {
//                Date studyDate = new Date(testDate.getTime() - ((long) (days - i - 1) * 1000 * 60 * 60 * 24));
//                UserStudyPlannerPage plannerPage = new UserStudyPlannerPage(user, studyDate, studyDays.get(i));
//                user.addStudyPlannerPage(plannerPage);
//            }
//        }
//        
//
//    public ArrayList<Deck> getStudyDecksForToday() {
//        ArrayList<Deck> todayDecks = new ArrayList<>();
//        LocalDate today = LocalDate.now();
//
//        for (Deck deck : selectedDecks) {
//            if (deck.getNextStudyDate().isEqual(today)) {
//                todayDecks.add(deck);
//            }
//        }
//
//        return todayDecks;
//    }
//
//    public ArrayList<Deck> getRepeatDecksForToday() {
//        ArrayList<Deck> todayDecks = new ArrayList<>();
//        LocalDate today = LocalDate.now();
//
//        for (Deck deck : deckRepeatCount.keySet()) {
//            if (deckRepeatCount.get(deck) > 0 && deck.getNextRepeatDate().isEqual(today)) {
//                todayDecks.add(deck);
//            }
//        }
//
//        return todayDecks;
//    }
//
//    public ArrayList<Deck> getStudyDecksForTomorrow() {
//        ArrayList<Deck> tomorrowDecks = new ArrayList<>();
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//
//        for (Deck deck : selectedDecks) {
//            if (deck.getNextStudyDate().isEqual(tomorrow)) {
//                tomorrowDecks.add(deck);
//            }
//        }
//
//        return tomorrowDecks;
//    }
//
//    public ArrayList<Deck> getRepeatDecksForTomorrow() {
//        ArrayList<Deck> tomorrowDecks = new ArrayList<>();
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//
//        for (Deck deck : deckRepeatCount.keySet()) {
//            if (deckRepeatCount.get(deck) > 0 && deck.getNextRepeatDate().isEqual(tomorrow)) {
//                tomorrowDecks.add(deck);
//            }
//        }
//
//        return tomorrowDecks;
//    }
//
//    public ArrayList<Deck> getAllStudyDecks() {
//        return selectedDecks;
//    }
//
//    public ArrayList<Deck> getAllRepeatDecks() {
//        ArrayList<Deck> repeatDecks = new ArrayList<>();
//
//        for (Deck deck : deckRepeatCount.keySet()) {
//            if (deckRepeatCount.get(deck) > 0) {
//                repeatDecks.add(deck);
//            }
//        }
//
//        return repeatDecks;
//    }
//
//    public void updateDeckRepeatCount(Deck deck, boolean isCorrect) {
//        if (isCorrect) {
//            // If the user gets the deck correct, reset the repeat count to 0
//            deckRepeatCount.put(deck, 0);
//        } else {
//            // If the user gets the deck incorrect, increment the repeat count
//            int currentCount = deckRepeatCount.get(deck);
//            int maxCount = deck.getRepeatFrequency();
//            if (currentCount < maxCount) {
//                deckRepeatCount.put(deck, currentCount + 1);
//            }
//        }
//        
//        // Update the next repeat date for the deck
//        LocalDate currentDate = LocalDate.now();
//        int daysUntilNextRepeat = deck.getRepeatInterval() * (deckRepeatCount.get(deck) + 1);
//        LocalDate nextRepeatDate = currentDate.plusDays(daysUntilNextRepeat);
//        deck.setNextRepeatDate(nextRepeatDate);
//        
//        // Update the next study date for the deck
//        int daysUntilNextStudy = deck.getStudyInterval();
//        if (deckRepeatCount.get(deck) > 0) {
//            daysUntilNextStudy = daysUntilNextRepeat / deck.getRepeatFrequency();
//        }
//        LocalDate nextStudyDate = currentDate.plusDays(daysUntilNextStudy);
//        deck.setNextStudyDate(nextStudyDate);
//    }
//
//	public ArrayList<Deck> getStudyDecksForToday() {
//        ArrayList<Deck> todayDecks = new ArrayList<>();
//        LocalDate today = LocalDate.now();
//
//        for (Deck deck : selectedDecks) {
//            if (deck.getNextStudyDate().isEqual(today)) {
//                todayDecks.add(deck);
//            }
//        }
//
//        return todayDecks;
//    }
//	
//	public ArrayList<Deck> getRepeatDecksForToday() {
//        ArrayList<Deck> todayDecks = new ArrayList<>();
//        LocalDate today = LocalDate.now();
//
//        for (Deck deck : deckRepeatCount.keySet()) {
//            if (deckRepeatCount.get(deck) > 0 && deck.getNextRepeatDate().isEqual(today)) {
//                todayDecks.add(deck);
//            }
//        }
//
//        return todayDecks;
//    }
//
//    public ArrayList<Deck> getStudyDecksForTomorrow() {
//        ArrayList<Deck> tomorrowDecks = new ArrayList<>();
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//
//        for (Deck deck : selectedDecks) {
//            if (deck.getNextStudyDate().isEqual(tomorrow)) {
//                tomorrowDecks.add(deck);
//            }
//        }
//
//        return tomorrowDecks;
//    }
//    
//    public ArrayList<Deck> getRepeatDecksForTomorrow() {
//        ArrayList<Deck> tomorrowDecks = new ArrayList<>();
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//
//        for (Deck deck : deckRepeatCount.keySet()) {
//            if (deckRepeatCount.get(deck) > 0 && deck.getNextRepeatDate().isEqual(tomorrow)) {
//                tomorrowDecks.add(deck);
//            }
//        }
//
//        return tomorrowDecks;
//    }
// 
//    public void updateDeckRepeatCount(Deck deck, boolean isCorrect) {
//        if (isCorrect) {
//            // If the user gets the deck correct, reset the repeat count to 0
//            deckRepeatCount.put(deck, 0);
//        } else {
//            // If the user gets the deck incorrect, increment the repeat count
//            int currentCount = deckRepeatCount.get(deck);
//            int maxCount = deck.getRepeatFrequency();
//            if (currentCount < maxCount) {
//                deckRepeatCount.put(deck, currentCount + 1);
//            }
//        }
//    }
//   