package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    private JTextField testDateField;
    private JTextField frequencyField;
   private JComboBox<String> difficultyComboBox;
    private JTextField studyTimeField;
    private ArrayList<Deck> publicDecks = new ArrayList<>();
    private ArrayList<Deck> userDecks = new ArrayList<>();
    private ArrayList<Deck> allDecks;
    private ArrayList<Deck> decks;
    private ArrayList<JCheckBox> deckCheckBoxes;
    private JButton submitButton;
    private JButton backButton;
    private User user;
    private Controller controller;
    private DeckList decklist;
    private JDBC mysql_database;

    public CreateStudyPlanPage(User user, ArrayList<Deck> decks) {
		this.user = user;
        this.decks = decks;
        deckCheckBoxes = new ArrayList<JCheckBox>();
        this.mysql_database = new JDBC();
        // Retrieve the public decks and user decks from the database
//        publicDecks = decklist.getAllPublicDecks();
//        System.out.print(publicDecks);
        // Retrieve all public decks
        //ArrayList<Deck> publicDecks = controller.allPublicDecks();
        publicDecks = mysql_database.publicDeckList();
        userDecks = mysql_database.userDeckList();
        System.out.print(publicDecks);
        System.out.print(userDecks);
        // Retrieve all user decks
        //ArrayList<Deck> userDecks = controller.allUserDecks(user_id);
//        userDecks = decklist.getAllCurrentUserDecks();
        
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
        
        // Create a panel to input test date
        JPanel testDatePanel = new JPanel(new FlowLayout());
        JLabel testDateLabel = new JLabel("Test Date: ");
        testDateField = new JTextField(10);
        testDatePanel.add(testDateLabel);
        testDatePanel.add(testDateField);

        // Create a panel to input flashcard frequency
        JPanel frequencyPanel = new JPanel(new FlowLayout());
        JLabel frequencyLabel = new JLabel("Flashcard Frequency: ");
        frequencyField = new JTextField(10);
        frequencyPanel.add(frequencyLabel);
        frequencyPanel.add(frequencyField);

        // Create a panel to select flashcard difficulty
        JPanel difficultyPanel = new JPanel(new FlowLayout());
        JLabel difficultyLabel = new JLabel("Flashcard Difficulty: ");
        String[] difficulties = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<String>(difficulties);
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyComboBox);

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
				
			}
		});
        submitPanel.add(backButton);
        submitPanel.add(submitButton);


        JLabel studyTimeLabel = new JLabel("Study Time: ");
        // Create a panel to input study time
        JPanel studyTimePanel = new JPanel(new FlowLayout());
        studyTimeField = new JTextField(10);
        studyTimePanel.add(studyTimeLabel);
        studyTimePanel.add(studyTimeField);
        
        // Add test date panel to contentPanel
        contentPanel.add(testDatePanel);        
        // Add frequency panel to contentPanel
        contentPanel.add(frequencyPanel);        
        // Add difficulty panel to contentPanel
        contentPanel.add(difficultyPanel);        
        // Add study time panel to contentPanel
        contentPanel.add(studyTimePanel);
        // Add deck panel to contentPanel
        contentPanel.add(deckPanel);       
        // Add submit panel to contentPanel
        contentPanel.add(submitPanel);
        // Add the contentPanel to the center of the main panel
        add(contentPanel, BorderLayout.CENTER);
        
        // Set frame properties
        setName("Create Study Plan");
        setSize(400, 400);
        //setLocation(null);
        //setDefaultCloseOperation(JPanel.EXIT_ON_CLOSE);
        setVisible(true);

    }

	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String testDate = testDateField.getText();
            int frequency = Integer.parseInt(frequencyField.getText());
            String difficulty = difficultyComboBox.getSelectedItem().toString();
            int studyTime = Integer.parseInt(studyTimeField.getText());
            ArrayList<Deck> selectedDecks = new ArrayList<Deck>();
            for (int i = 0; i < deckCheckBoxes.size(); i++) {
                if (deckCheckBoxes.get(i).isSelected()) {
                    selectedDecks.add(decks.get(i));
                }
            }

            // Create a new StudyPlan based on the user's inputs
            StudyPlan studyPlan = new StudyPlan(testDate, frequency, difficulty, studyTime, selectedDecks);
            // Print the study plan for testing purposes
            System.out.println(studyPlan);
            
//            StudyPlan studyPlanList = new StudyPlanList();
//            studyPlanList.addStudyPlan(studyPlan);
            
         // Get the parent JFrame of the JPanel
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Call dispose() on the parent JFrame
            frame.dispose();

            UserStudyPlannerPage studyPlannerPage = new UserStudyPlannerPage(user);
            studyPlannerPage.setVisible(true);
        }
    }

//    public static void main(String[] args) {
//        // Create some test decks
//        ArrayList<Deck> decks = new ArrayList<Deck>();
//        decks.add(new Deck("Deck 1"));
//        decks.add(new Deck("Deck 2"));
//        decks.add(new Deck("Deck 3"));
//
//        // Create a new StudyPlannerGUI and show it
//        UserStudyPlannerPage gui = new UserStudyPlannerPage(decks);
//        gui.setVisible(true);
//    }
}

            
