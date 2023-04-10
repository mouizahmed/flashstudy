package Views;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Controller.Controller;
import Models.Deck;
import Models.StudyPlan;
import Models.User;

public class UserStudyPlannerPageTest {
    private User user;
    private Controller controller;
    private ArrayList<StudyPlan> studyPlans;

    @Before
    public void setUp() throws Exception {
    	User user = new User("testUser", "password123", null, null);
        ArrayList<Deck> decks = new ArrayList<>();
        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        Controller controller = new Controller(mainPanel, cardLayout);
        studyPlans = new ArrayList<>();
        UserStudyPlannerPage page = new UserStudyPlannerPage(user, studyPlans, controller);
    }

    @Test
    public void testUserStudyPlannerPage() {
        User user = new User("testUser", "password123", null, null);
        ArrayList<StudyPlan> studyPlans = new ArrayList<>();
        Controller controller = new Controller(new JPanel(), new CardLayout());
        UserStudyPlannerPage page = new UserStudyPlannerPage(user, studyPlans, controller);
        assertEquals(621, page.getPreferredSize().width);
        assertEquals(245, page.getPreferredSize().height);
        assertNotNull(page.currentDateLabel);
        assertNotNull(page.quizButton);
        assertNotNull(page.studyDecksList);
        assertNotNull(page.backButton);
        assertNotNull(page.user);
        assertNotNull(page.controller);
        assertNotNull(page.mysql_database);
        assertNotNull(page.studyPlanScrollPane);
        assertNotNull(page.listPanel);
        assertNotNull(page.deleteButton);
    }

}  
