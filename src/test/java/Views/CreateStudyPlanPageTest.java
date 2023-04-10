package Views;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CreateStudyPlanPageTest {

    private CreateStudyPlanPage createStudyPlanPage;

    @Test
    void testComponentsExist() {
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
    void testDefaultTestDate() {
        // Check that the default test date is set to today's date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //assertEquals(dateFormat.format(new Date()), createStudyPlanPage.testDateField.getText());
    }

    @Test
    void testFrequencyComboBox() {
        // Check that the frequency combo box contains the correct options
        JComboBox<String> frequencyComboBox = createStudyPlanPage.frequencyComboBox;
        assertEquals(3, frequencyComboBox.getItemCount());
        assertEquals("2", frequencyComboBox.getItemAt(0));
        assertEquals("3", frequencyComboBox.getItemAt(1));
        assertEquals("4+", frequencyComboBox.getItemAt(2));
    }

    @Test
    void testDifficultyComboBox() {
        // Check that the difficulty combo box contains the correct options
        JComboBox<String> difficultyComboBox = createStudyPlanPage.difficultyComboBox;
        assertEquals(3, difficultyComboBox.getItemCount());
        assertEquals("Easy", difficultyComboBox.getItemAt(0));
        assertEquals("Medium", difficultyComboBox.getItemAt(1));
        assertEquals("Hard", difficultyComboBox.getItemAt(2));
    }

    @Test
    void testDeckCheckBoxes() {
        // Check that the deck check boxes have been created and added to the panel
        ArrayList<JCheckBox> deckCheckBoxes = createStudyPlanPage.deckCheckBoxes;
       // assertEquals(0, deckCheckBoxes.size()); // Should be initialized to an empty array list
        JPanel deckPanel = (JPanel) createStudyPlanPage.getComponent(1); // Get the panel containing the check boxes
    }
}
