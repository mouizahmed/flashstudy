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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controller.Controller;
import Models.Deck;
import Models.DeckList;
import Models.JDBC;
import Models.StudyPlan;
import Models.User;

public class CreateStudyPlanPage extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JFormattedTextField testDateField;
    private JComboBox<String> frequencyComboBox;
    private JComboBox<String> difficultyComboBox;
    private JTextField studyTimeDaysField;
    private JTextField studyPlanTitleField;
    private JComboBox<String> studyTimeComboBox;
    private ArrayList<Deck> publicDecks = new ArrayList<>();
    private ArrayList<Deck> userDecks = new ArrayList<>();
    private ArrayList<Deck> allDecks;
    private ArrayList<Deck> decks;
    private ArrayList<JCheckBox> deckCheckBoxes;
    private JButton submitButton;
    private JButton backButton;
    private User user;
    private Controller controller;
    private JDBC mysql_database;

    public CreateStudyPlanPage(User user, ArrayList<Deck> decks, Controller controller) {
		this.user = user;
        this.decks = decks;
        this.controller = controller;
        deckCheckBoxes = new ArrayList<JCheckBox>();
       // this.mysql_database = controller.getJdbc();

        try {
			this.mysql_database = new JDBC();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Retrieve the public decks and user decks from the database
        publicDecks = controller.allPublicDecks();//mysql_database.publicDeckList();
        userDecks = controller.allUserDecks();//mysql_database.userDeckList();
//        System.out.print(publicDecks);
//        System.out.print(userDecks);
        
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

//        JLabel testDateLabel = new JLabel("Test Date (DD/MM/YYYY): ");
//        testDateField = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
//        testDateField.setColumns(10);
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
	            int studyTimeDays = Integer.parseInt(studyTimeDaysField.getText());
	            ArrayList<Deck> selectedDecks = new ArrayList<Deck>();
	            for (int i = 0; i < deckCheckBoxes.size(); i++) {
	                if (deckCheckBoxes.get(i).isSelected()) {
	                    selectedDecks.add(allDecks.get(i));
	                }
	            }
	            
	           // Create a new StudyPlan based on the user's inputs
	            StudyPlan studyPlan = new StudyPlan(createdBy, studyPlanID, studyPlanTitle, testDate, frequency, difficulty, studyTime, studyTimeDays, selectedDecks);
	            
	            //String studyPlanID = UUID.randomUUID().toString();
	            StudyPlan createStudyPlan = mysql_database.createStudyPlan(createdBy, studyPlanID, studyPlanTitle, testDate, frequency, difficulty, studyTime, studyTimeDays, selectedDecks);

	            // Print the study plan for testing purposes
	            System.out.println(studyPlan);

                // retrieve the study plan from the database
                //StudyPlan retrievedStudyPlan = mysql_database.getAllStudyPlansByUser(user.getUsername());

                // display the study plan using the controller
                //controller.UserStudyPlannerPage(user, retrievedStudyPlan);
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
        
        JLabel studyTimeLabel = new JLabel("Study Time in total (# of days): ");
        studyTimeDaysField = new JTextField(10);
        studyTimePanel.add(studyTimeLabel);
        studyTimePanel.add(studyTimeDaysField);
        
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

	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String testDate = testDateField.getText();
            String frequency = frequencyComboBox.getSelectedItem().toString();
            String difficulty = difficultyComboBox.getSelectedItem().toString();
            String studyTime = studyTimeComboBox.getSelectedItem().toString();
            int studyTimeDays = Integer.parseInt(studyTimeDaysField.getText());
            ArrayList<Deck> selectedDecks = new ArrayList<Deck>();
            for (int i = 0; i < deckCheckBoxes.size(); i++) {
                if (deckCheckBoxes.get(i).isSelected()) {
                    selectedDecks.add(decks.get(i));
                }
            }
            System.out.println(testDate);
            System.out.println(frequency);
            System.out.println(difficulty);
            System.out.println(studyTime);
            System.out.println(studyTimeDays);
            System.out.println(selectedDecks);

            // Create a new StudyPlan based on the user's inputs
            //StudyPlan studyPlan = new StudyPlan(testDate, frequency, difficulty, studyTime, studyTimeDays, selectedDecks);
            // Print the study plan for testing purposes
            //System.out.println(studyPlan);
            
//            StudyPlan studyPlanList = new StudyPlanList();
//            studyPlanList.addStudyPlan(studyPlan);
            
         // Get the parent JFrame of the JPanel
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Call dispose() on the parent JFrame
            frame.dispose();
        }
    }
}

            
