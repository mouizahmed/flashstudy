package Views;

import java.util.ArrayList;

import Models.Deck;
import Models.StudyPlan;
import Models.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;

import Controller.Controller;

public class UserStudyPlannerPage extends JPanel {
	    private StudyPlan studyPlan;
	    private JFrame frame;
	    private JPanel mainPanel;
	    private JLabel titleLabel;
	    private JLabel testDateLabel;
	    private JLabel currentDateLabel;
	    private JButton todayButton;
	    private JButton tomorrowButton;
	    private JButton allButton;
	    private JList<String> studyDecksList;
	    private JList<String> repeatDecksList;
	    private JButton backButton;
		private User user;
		private Controller controller;
	    
	    public UserStudyPlannerPage(User user, StudyPlan studyPlan, Controller controller) {
	    	this.user = user;
	        this.studyPlan = studyPlan;//new StudyPlan(studyPlan.createdBy, studyPlan.studyPlanID, studyPlan.studyPlanTitle, studyPlan.testDate, studyPlan.frequency, studyPlan.difficulty, 
	        		//studyPlan.studyTime, studyPlan.studyTimeDays, studyPlan.selectedDecks);
	        this.controller = controller;
	        System.out.print(studyPlan);

	        frame = new JFrame("Your Study Plan");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	        
	        titleLabel = new JLabel("Your Study Plan");
	        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(titleLabel);
	        
	        if (studyPlan != null) {
	            testDateLabel = new JLabel("Test Date: " + studyPlan.getTestDate());
	        } else {
	            testDateLabel = new JLabel("Test Date: ");
	        }
	        testDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(testDateLabel);
	        
	        LocalDate date = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String formattedDate = date.format(formatter);
	        currentDateLabel = new JLabel("Current Date: " + formattedDate);
	        //System.out.println(formattedDate); // prints something like "28/03/2023"

	        currentDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(currentDateLabel);
	        
	        todayButton = new JButton("Today's Study Decks");
	        todayButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        todayButton.addActionListener(e -> {
//	            displayStudyDecksList(studyPlan.getStudyDecksForToday());
//	            displayRepeatDecksList(studyPlan.getRepeatDecksForToday());
	        });
	        mainPanel.add(todayButton);
	        
	        tomorrowButton = new JButton("Tomorrow's Study Decks");
	        tomorrowButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        tomorrowButton.addActionListener(e -> {
//	            displayStudyDecksList(studyPlan.getStudyDecksForTomorrow());
//	            displayRepeatDecksList(studyPlan.getRepeatDecksForTomorrow());
	        });
	        mainPanel.add(tomorrowButton);
	        
	        allButton = new JButton("All Study Decks");
	        allButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        allButton.addActionListener(e -> {
	            displayStudyDecksList(studyPlan.getAllStudyDecks());
	            displayRepeatDecksList(studyPlan.getAllRepeatDecks());
	        });
	        mainPanel.add(allButton);
	        
	        studyDecksList = new JList<>();
	        repeatDecksList = new JList<>();
	        JScrollPane studyScrollPane = new JScrollPane(studyDecksList);
	        JScrollPane repeatScrollPane = new JScrollPane(repeatDecksList);
	        JPanel listPanel = new JPanel();
	        listPanel.add(studyScrollPane);
	        listPanel.add(repeatScrollPane);
	        mainPanel.add(listPanel);
	        
	        backButton = new JButton("Back");
	        backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.landingPage();
				}
			});
	        mainPanel.add(backButton);
	        
//	        setName("Create Study Plan");
//	        setSize(400, 400);
//	        setVisible(true);
	        
	        frame.getContentPane().add(mainPanel);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }
	    
	    private void displayStudyDecksList(ArrayList<Deck> studyDecks) {
	        DefaultListModel<String> studyListModel = new DefaultListModel<>();
	        for (Deck deck : studyDecks) {
	            studyListModel.addElement(deck.getDeckTitle());
	        }
	        studyDecksList.setModel(studyListModel);
	    }
	    
	    private void displayRepeatDecksList(ArrayList<Deck> repeatDecks) {
	        DefaultListModel<String> repeatListModel = new DefaultListModel<>();
	        for (Deck deck : repeatDecks) {
	            repeatListModel.addElement(deck.getDeckTitle());
	        }
	        repeatDecksList.setModel(repeatListModel);
	    }

}
