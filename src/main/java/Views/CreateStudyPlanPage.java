package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.Controller;
import Models.Deck;
import Models.JDBC;
import Models.StudyPlan;
import Models.User;

public class CreateStudyPlanPage extends JPanel {
    private static final long serialVersionUID = 1L;

    public JFormattedTextField testDateField;
    public JComboBox<String> frequencyComboBox;
    public JComboBox<String> difficultyComboBox;
    public JTextField studyPlanTitleField;
    public JComboBox<String> studyTimeComboBox;
    public ArrayList<Deck> publicDecks = new ArrayList<>();
    public ArrayList<Deck> userDecks = new ArrayList<>();
    public ArrayList<Deck> allDecks;
    public ArrayList<Deck> decks;
    public ArrayList<JCheckBox> deckCheckBoxes;
    public JButton submitButton;
    public JButton backButton;
    public User user;
    public Controller controller;
    public JDBC mysql_database;

    public CreateStudyPlanPage(User user, ArrayList<Deck> decks, Controller controller) {
		this.user = user;
        this.decks = decks;
        this.controller = controller;
        deckCheckBoxes = new ArrayList<JCheckBox>();

        try {
			this.mysql_database = new JDBC();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
        
        // Retrieve the public decks and user decks from the database
        publicDecks = controller.allPublicDecks();
        userDecks = controller.allUserDecks();
        
        // Combine the public and user decks into a single list
        allDecks = new ArrayList<Deck>();
        allDecks.addAll(publicDecks);
        allDecks.addAll(userDecks);
        System.out.print(allDecks);
        
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add title to the top
        JLabel titleLabel = new JLabel("Create Study Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        
        // Create a JPanel to hold the content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        
        JPanel studyPlanTitlePanel = new JPanel(new FlowLayout());
        JLabel studyPlanTitleLabel = new JLabel("Name for study plan");
        studyPlanTitleField = new JTextField(10);
        studyPlanTitlePanel.add(studyPlanTitleLabel);
        studyPlanTitlePanel.add(studyPlanTitleField);
        
       
        // Create a panel to input test date
        JPanel testDatePanel = new JPanel(new FlowLayout());
        
        // Create a label and a formatted text field for the test date
        JLabel testDateLabel = new JLabel("Test date (DD/MM/YYYY): ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JFormattedTextField testDateField = new JFormattedTextField(dateFormat);
        testDateField.setColumns(10);
        testDateField.setValue(new Date()); // Set the default value to today's date
        testDateField.setToolTipText("Enter the date of your test in the format DD/MM/YYYY");

        testDatePanel.add(testDateLabel);
        testDatePanel.add(testDateField);

        // Create a panel to input flashcard frequency
        JPanel  frequency_difficultyPanel = new JPanel(new FlowLayout());
        JLabel frequencyLabel = new JLabel("Flashcard Frequency: ");
        String[] frequencies = {"2", "3", "4+"};
        frequencyComboBox = new JComboBox<String>(frequencies);
        frequency_difficultyPanel.add(frequencyLabel);
        frequency_difficultyPanel.add(frequencyComboBox);

        // Create a panel to select flashcard difficulty
        JLabel difficultyLabel = new JLabel("Flashcard Difficulty: ");
        String[] difficulties = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<String>(difficulties);
        frequency_difficultyPanel.add(difficultyLabel);
        frequency_difficultyPanel.add(difficultyComboBox);

        // Create a panel to select deck(s) to study from
        JPanel deckPanel = new JPanel(new FlowLayout());
        JLabel deckLabel = new JLabel("Decks to study from: ");
        // Create the checkboxes for all decks
        deckPanel.add(deckLabel);
        for (Deck deck : allDecks) {
            JCheckBox checkBox = new JCheckBox(deck.getDeckTitle());
            deckCheckBoxes.add(checkBox);
            deckPanel.add(checkBox);
        }
        JScrollPane deckScrollPane = new JScrollPane(deckPanel);
        deckScrollPane.setPreferredSize(new Dimension(400, 150));
        deckScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Set the layout manager of deckPanel to BoxLayout with a vertical axis
        deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.Y_AXIS));

        // Create a panel for the back & submit button
        JPanel submitPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.landingPage();
			}
		});
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String createdBy = user.getUsername();
				String studyPlanID = UUID.randomUUID().toString();
				String studyPlanTitle = studyPlanTitleField.getText();
				String testDate = testDateField.getText();
	            String frequency = frequencyComboBox.getSelectedItem().toString();
	            String difficulty = difficultyComboBox.getSelectedItem().toString();
	            String studyTime = studyTimeComboBox.getSelectedItem().toString();
	            ArrayList<Deck> selectedDecks = new ArrayList<Deck>();
	            for (int i = 0; i < deckCheckBoxes.size(); i++) {
	                if (deckCheckBoxes.get(i).isSelected()) {
	                    selectedDecks.add(allDecks.get(i));
	                }
	            }
	            mysql_database.createStudyPlan(createdBy, studyPlanID, studyPlanTitle, testDate, frequency, difficulty, studyTime, selectedDecks);
	            
	           // Create a new StudyPlan based on the user's inputs
	            StudyPlan studyPlan = new StudyPlan(createdBy, studyPlanID, studyPlanTitle, testDate, frequency, difficulty, studyTime, selectedDecks);

	            // Print the study plan for testing purposes
	            System.out.println(studyPlan);
	            
	            // Display message to user
	    	    JOptionPane.showMessageDialog(null, "Study Plan: \"" + studyPlanTitle + "\" created successfully! View it in Your Study Plan page.", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
        submitPanel.add(backButton);
        submitPanel.add(submitButton);


        JLabel studyTimeDaysLabel = new JLabel("Study Time each day: ");
        // Create a panel to input study time
        JPanel studyTimePanel = new JPanel(new FlowLayout());
        String[] studyTimes = {"30 mins", "1 hour", "1.5 hours"};
        studyTimeComboBox = new JComboBox<String>(studyTimes);
        studyTimePanel.add(studyTimeDaysLabel);
        studyTimePanel.add(studyTimeComboBox);
        
        // Add title panel to contentPanel
        contentPanel.add(studyPlanTitlePanel);
        // Add test date panel to contentPanel
        contentPanel.add(testDatePanel);                
        // Add difficulty panel to contentPanel
        contentPanel.add(frequency_difficultyPanel);        
        // Add study time panel to contentPanel
        contentPanel.add(studyTimePanel);
        // Add deck panel to contentPanel
        contentPanel.add(deckScrollPane);       
        // Add submit panel to contentPanel
        contentPanel.add(submitPanel);
        // Add the contentPanel to the center of the main panel
        add(contentPanel, BorderLayout.CENTER);
        
        // Set frame properties
        setName("Create Study Plan");
        setSize(400, 400);
        setVisible(true);

    }
}