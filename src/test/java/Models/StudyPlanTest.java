package Models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class StudyPlanTest {
    
    private StudyPlan studyPlan;
    private ArrayList<Deck> decks;

    @Before
    public void setUp() {
        decks = new ArrayList<>();
        decks.add(new Deck("deck1", new ArrayList<>(), "user", true, "1", "school", "faculty", "description", "course"));
        decks.add(new Deck("deck2", new ArrayList<>(), "user", true, "2", "school", "faculty", "description", "course"));
        studyPlan = new StudyPlan("user", "plan1", "My Study Plan", "01/04/2023", "5", "Medium", "60", decks);
    }

    @Test
    public void testGetStudyPlanTitle() {
        assertEquals("My Study Plan", studyPlan.getStudyPlanTitle());
    }

    @Test
    public void testGetStudyPlanID() {
        assertEquals("plan1", studyPlan.getStudyPlanID());
    }

    @Test
    public void testGetSelectedDecks() {
        assertEquals(decks, studyPlan.getSelectedDecks());
    }

    @Test
    public void testGetAllStudyDecks() {
        assertEquals(decks, studyPlan.getAllStudyDecks());
    }

    @Test
    public void testGetAllRepeatDecks() {
        ArrayList<Deck> repeatDecks = studyPlan.getAllRepeatDecks();
        assertTrue(repeatDecks.isEmpty());
        studyPlan.updateStudyDecks();
        repeatDecks = studyPlan.getAllRepeatDecks();
        assertFalse(repeatDecks.isEmpty());
    }

    @Test
    public void testGetTestDate() {
        assertEquals("01/04/2023", studyPlan.getTestDate());
    }

    @Test
    public void testSetTestDate() {
        studyPlan.setTestDate("02/04/2023");
        assertEquals("02/04/2023", studyPlan.getTestDate());
    }

    @Test
    public void testGetRepeatThreshold() {
        assertEquals(5, studyPlan.getRepeatThreshold());
    }

    @Test
    public void testUpdateStudyDecks() {
        studyPlan.updateStudyDecks();
        ArrayList<Deck> allStudyDecks = studyPlan.getAllStudyDecks();
        ArrayList<Deck> allRepeatDecks = studyPlan.getAllRepeatDecks();
        assertFalse(allStudyDecks.isEmpty());
    }

    @Test
    public void testUpdateStudyDecksWithRepeatDecks() {
        decks.get(0).setCounter(3);
        decks.get(1).setCounter(5);
        ArrayList<Deck> allStudyDecks = studyPlan.getAllStudyDecks();
        ArrayList<Deck> allRepeatDecks = studyPlan.getAllRepeatDecks();
        assertFalse(allStudyDecks.isEmpty());
    }
}