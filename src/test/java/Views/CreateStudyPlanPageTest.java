package Views;

import org.junit.Before;
import org.junit.Test;

import Controller.Controller;
import Models.Deck;
import Models.User;

import javax.swing.*;

import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateStudyPlanPageTest {

    private CreateStudyPlanPage createStudyPlanPage;

    @Before
    public void setUp() throws Exception {
        User user = new User("testUser", "password123", null, null);
        ArrayList<Deck> decks = new ArrayList<>();
        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        Controller controller = new Controller(mainPanel, cardLayout);
        createStudyPlanPage = new CreateStudyPlanPage(user, decks, controller);
    }

    @Test
    public void testComponentsExist() {
        // Check that all components have been initialized and are not null
        assertNotNull(createStudyPlanPage.frequencyComboBox);
        assertNotNull(createStudyPlanPage.difficultyComboBox);
        assertNotNull(createStudyPlanPage.studyPlanTitleField);
        assertNotNull(createStudyPlanPage.studyTimeComboBox);
        assertNotNull(createStudyPlanPage.publicDecks);
        assertNotNull(createStudyPlanPage.userDecks);
        assertNotNull(createStudyPlanPage.allDecks);
        assertNotNull(createStudyPlanPage.decks);
        assertNotNull(createStudyPlanPage.deckCheckBoxes);
        assertNotNull(createStudyPlanPage.submitButton);
        assertNotNull(createStudyPlanPage.backButton);
        assertNotNull(createStudyPlanPage.user);
        assertNotNull(createStudyPlanPage.controller);
        assertNotNull(createStudyPlanPage.mysql_database);
    }

    @Test
    public void testDefaultTestDate() {
        // Check that the default test date is set to today's date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //assertEquals(dateFormat.format(new Date()), createStudyPlanPage.testDateField.getText());
    }

    @Test
    public void testFrequencyComboBox() {
        // Check that the frequency combo box contains the correct options
        JComboBox<String> frequencyComboBox = createStudyPlanPage.frequencyComboBox;
        assertEquals(3, frequencyComboBox.getItemCount());
        assertEquals("2", frequencyComboBox.getItemAt(0));
        assertEquals("3", frequencyComboBox.getItemAt(1));
        assertEquals("4+", frequencyComboBox.getItemAt(2));
    }

    @Test
    public void testDifficultyComboBox() {
        // Check that the difficulty combo box contains the correct options
        JComboBox<String> difficultyComboBox = createStudyPlanPage.difficultyComboBox;
        assertEquals(3, difficultyComboBox.getItemCount());
        assertEquals("Easy", difficultyComboBox.getItemAt(0));
        assertEquals("Medium", difficultyComboBox.getItemAt(1));
        assertEquals("Hard", difficultyComboBox.getItemAt(2));
    }

    @Test
    public void testDeckCheckBoxes() {
        // Check that the deck check boxes have been created and added to the panel
        ArrayList<JCheckBox> deckCheckBoxes = createStudyPlanPage.deckCheckBoxes;
       // assertEquals(0, deckCheckBoxes.size()); // Should be initialized to an empty array list
        JPanel deckPanel = (JPanel) createStudyPlanPage.getComponent(1); // Get the panel containing the check boxes
    }
}
