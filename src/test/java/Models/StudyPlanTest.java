package Models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class StudyPlanTest {
    
    private StudyPlan studyPlan;
    private ArrayList<Deck> decks;

    @Test
    void testGetStudyPlanTitle() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        assertEquals("My Study Plan", studyPlan.getStudyPlanTitle());
    }

    @Test
    void testGetStudyPlanID() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        assertEquals("plan1", studyPlan.getStudyPlanID());
    }

    @Test
    void testGetSelectedDecks() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        assertEquals(decks, studyPlan.getSelectedDecks());
    }

    @Test
    void testGetAllStudyDecks() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        assertEquals(decks, studyPlan.getAllStudyDecks());
    }

    @Test
    void testGetAllRepeatDecks() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        ArrayList<Deck> repeatDecks = studyPlan.getAllRepeatDecks();
        assertTrue(repeatDecks.isEmpty());
        studyPlan.updateStudyDecks();
        repeatDecks = studyPlan.getAllRepeatDecks();
        assertFalse(repeatDecks.isEmpty());
    }

    @Test
    void testGetTestDate() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        assertEquals("01/04/2023", studyPlan.getTestDate());
    }

    @Test
    void testSetTestDate() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        studyPlan.setTestDate("02/04/2023");
        assertEquals("02/04/2023", studyPlan.getTestDate());
    }

    @Test
    void testGetRepeatThreshold() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        assertEquals(5, studyPlan.getRepeatThreshold());
    }

    @Test
    void testUpdateStudyDecks() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        studyPlan.updateStudyDecks();
        ArrayList<Deck> allStudyDecks = studyPlan.getAllStudyDecks();
        ArrayList<Deck> allRepeatDecks = studyPlan.getAllRepeatDecks();
        assertFalse(allStudyDecks.isEmpty());
    }

    @Test
    void testUpdateStudyDecksWithRepeatDecks() {
    	decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
        decks.get(0).setCounter(3);
        decks.get(1).setCounter(5);
        ArrayList<Deck> allStudyDecks = studyPlan.getAllStudyDecks();
        ArrayList<Deck> allRepeatDecks = studyPlan.getAllRepeatDecks();
        assertFalse(allStudyDecks.isEmpty());
    }
    
    @Test
    void testCreateStudyPlan() {
        // Mock data
        String createdBy = "testCreatedBy";
        String studyPlanID = "testStudyPlanID";
        String studyPlanTitle = "testStudyPlanTitle";
        String testDate = "testTestDate";
        String frequency = "testFrequency";
        String difficulty = "testDifficulty";
        String studyTime = "testStudyTime";
        ArrayList<Deck> selectedDecks = new ArrayList<>();
        Deck deck = null;
		selectedDecks.add(deck);

        // Test createStudyPlan method
        StudyPlan createdStudyPlan = new StudyPlan(createdBy, studyPlanID, studyPlanTitle, testDate, frequency, difficulty, studyTime, selectedDecks);

        // Assert
        assertNotNull(createdStudyPlan);
        assertEquals(studyPlanID, createdStudyPlan.getStudyPlanID());
        assertEquals(studyPlanTitle, createdStudyPlan.getStudyPlanTitle());
        assertEquals(testDate, createdStudyPlan.getTestDate());
        assertEquals(selectedDecks, createdStudyPlan.getSelectedDecks());
    }
}