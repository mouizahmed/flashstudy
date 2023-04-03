package Views;

import java.util.ArrayList;

import Models.Deck;
import Models.DeckList;
import Models.JDBC;
import Models.StudyPlan;
import Models.User;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
	    private JLabel studyPlanTitleLabel;
	    private JLabel testDateLabel;
	    private JLabel currentDateLabel;
	    private JButton quizButton;
	    private JButton todayButton;
	    private JButton tomorrowButton;
	    private JButton allButton;
	    private JList<String> studyDecksList;
	    private JList<String> repeatDecksList;
	    private JButton backButton;
		private User user;
		private Controller controller;
	    private JDBC mysql_database;
	    
	    public UserStudyPlannerPage(User user, StudyPlan studyPlan, Controller controller) {
	    	this.user = user;
	        this.studyPlan = studyPlan;
	        this.controller = controller;

	        try {
				this.mysql_database = new JDBC();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
	        
	        setLayout(new BorderLayout());
	        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        
	        mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	        
	        titleLabel = new JLabel("Your Study Plan");
	        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(titleLabel);
	        
	        if (studyPlan != null) {
	        	studyPlanTitleLabel = new JLabel("Name of Study Plan: " + studyPlan.studyPlanTitle);
	            testDateLabel = new JLabel("Test Date: " + studyPlan.getTestDate());
	        } else {
	            testDateLabel = new JLabel("Test Date: ");
	        }
	        studyPlanTitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        testDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(studyPlanTitleLabel);
	        mainPanel.add(testDateLabel);
	        
	        LocalDate date = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String formattedDate = date.format(formatter);
	        currentDateLabel = new JLabel("Current Date: " + formattedDate);
	        //System.out.println(formattedDate); // prints something like "28/03/2023"

	        currentDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(currentDateLabel);
	        
	        allButton = new JButton("All Study Decks");
	        allButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        allButton.addActionListener(e -> {
	            displayStudyDecksList(studyPlan.selectedDecks);
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

	        quizButton = new JButton("Start Quiz Session"); //new button added
	        quizButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        quizButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String selectedDeckTitle = studyDecksList.getSelectedValue();
	                if (selectedDeckTitle == null) {
	                    JOptionPane.showMessageDialog(null, "Please select a deck to study!");
	                    return;
	                }
	                Deck selectedDeck = mysql_database.getDeckByTitle(selectedDeckTitle);
	                controller.quiz(selectedDeck);
	            }
	        });
	        mainPanel.add(quizButton);
	        //Changes needed for display!!
	        backButton = new JButton("Back");
	        backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.landingPage();
				}
			});
	        mainPanel.add(backButton);
	        add(mainPanel);//, BorderLayout.CENTER);
	        setSize(400, 400);
	        setVisible(true);

	    }
	    
	    private void displayStudyDecksList(ArrayList<Deck> studyDecks) {
	        DefaultListModel<String> studyListModel = new DefaultListModel<>();
	        for (Deck deck : studyDecks) {
	            studyListModel.addElement(deck.getDeckTitle());
	        }
	        studyDecksList.setModel(studyListModel);
	        studyDecksList.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                if (evt.getClickCount() == 2) {
	                    String selectedDeckTitle = studyDecksList.getSelectedValue();
	                    if (selectedDeckTitle != null) {
	                    	//Deck selectedDeck = this.JDBC.getDecksByTitle
	                        Deck selectedDeck = mysql_database.getDeckByTitle(selectedDeckTitle);
	                        controller.quiz(selectedDeck);
	                    }
	                }
	            }
	        });
	    }
	    
	    private void displayRepeatDecksList(ArrayList<Deck> repeatDecks) {
	        DefaultListModel<String> repeatListModel = new DefaultListModel<>();
	        for (Deck deck : repeatDecks) {
	            repeatListModel.addElement(deck.getDeckTitle());
	        }
	        repeatDecksList.setModel(repeatListModel);
	        repeatDecksList.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                if (evt.getClickCount() == 2) {
	                    String selectedDeckTitle = repeatDecksList.getSelectedValue();
	                    if (selectedDeckTitle != null) {
	                        Deck selectedDeck = mysql_database.getDeckByTitle(selectedDeckTitle);
	                        controller.quiz(selectedDeck);
	                    }
	                }
	            }
	        });
	    }

}
//todayButton = new JButton("Today's Study Decks");
//todayButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
//todayButton.addActionListener(e -> {
////    displayStudyDecksList(studyPlan.getStudyDecksForToday());
////    displayRepeatDecksList(studyPlan.getRepeatDecksForToday());
//});
//mainPanel.add(todayButton);
//
//tomorrowButton = new JButton("Tomorrow's Study Decks");
//tomorrowButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
//tomorrowButton.addActionListener(e -> {
////    displayStudyDecksList(studyPlan.getStudyDecksForTomorrow());
////    displayRepeatDecksList(studyPlan.getRepeatDecksForTomorrow());
//});
//mainPanel.add(tomorrowButton);