package Views;

import java.util.ArrayList;

import Models.Deck;
import Models.DeckList;
import Models.JDBC;
import Models.StudyPlan;
import Models.User;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
	    private ArrayList<StudyPlan> studyPlans;
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
	    private JScrollPane studyPlanScrollPane;
	    
//	    public UserStudyPlannerPage(User user, ArrayList<StudyPlan> retrievedStudyPlan, Controller controller) {
//	    	this.user = user;
//	        this.studyPlans = retrievedStudyPlan;
//	        this.controller = controller;
//	        
//	        for (StudyPlan studyPlan : retrievedStudyPlan) {
//	            String studyPlanTitle = studyPlan.getStudyPlanTitle();
//	            String studyPlanID = studyPlan.getStudyPlanID();
//	            System.out.println("Study Plan Title: " + studyPlanTitle + ", Study Plan ID: " + studyPlanID);
//	        }
//
//	        
//
//	        try {
//				this.mysql_database = new JDBC();
//			} catch (IOException | InterruptedException e) {
//				e.printStackTrace();
//			}
//	        
//	        studyPlan = this.mysql_database.getStudyPlanForUser(studyPlan.getStudyPlanID(), studyPlan.createdBy);
//	        setLayout(new BorderLayout());
//	        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//	        
//	        mainPanel = new JPanel();
//	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
//	        
//	        titleLabel = new JLabel("Your Study Plan");
//	        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//	        mainPanel.add(titleLabel);
//	        
//	        if (retrievedStudyPlan != null) {
//	        	studyPlanTitleLabel = new JLabel("Name of Study Plan: " + studyPlan.studyPlanTitle);
//	            testDateLabel = new JLabel("Test Date: " + studyPlan.getTestDate());
//	        } else {
//	        	studyPlanTitleLabel = new JLabel("Name of Study Plan: ");
//	            testDateLabel = new JLabel("Test Date: ");
//	        }
//	        studyPlanTitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//	        testDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//	        mainPanel.add(studyPlanTitleLabel);
//	        mainPanel.add(testDateLabel);
//	        
//	        LocalDate date = LocalDate.now();
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	        String formattedDate = date.format(formatter);
//	        currentDateLabel = new JLabel("Current Date: " + formattedDate);
//	        //System.out.println(formattedDate); // prints something like "28/03/2023"
//
//	        currentDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//	        mainPanel.add(currentDateLabel);
//	        
//	        allButton = new JButton("All Study Decks");
//	        allButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
//	        allButton.addActionListener(e -> {
//	            displayStudyDecksList(studyPlan.selectedDecks);
//	            displayRepeatDecksList(studyPlan.getAllRepeatDecks());
//	        });
//	        mainPanel.add(allButton);
//	        
//	        studyDecksList = new JList<>();
//	        repeatDecksList = new JList<>();
//	        JScrollPane studyScrollPane = new JScrollPane(studyDecksList);
//	        JScrollPane repeatScrollPane = new JScrollPane(repeatDecksList);
//	        JPanel listPanel = new JPanel();
//	        listPanel.add(studyScrollPane);
//	        listPanel.add(repeatScrollPane);
//	        mainPanel.add(listPanel);
//
//	        quizButton = new JButton("Start Quiz Session"); //new button added
//	        quizButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
//	        quizButton.addActionListener(new ActionListener() {
//	            public void actionPerformed(ActionEvent e) {
//	                String selectedDeckTitle = studyDecksList.getSelectedValue();
//	                if (selectedDeckTitle == null) {
//	                    JOptionPane.showMessageDialog(null, "Please select a deck to study!");
//	                    return;
//	                }
//	                Deck selectedDeck = mysql_database.getDeckByTitle(selectedDeckTitle);
//	                controller.quiz(selectedDeck);
//	            }
//	        });
//	        mainPanel.add(quizButton);
//
//	     // new panel added for displaying study decks and repeat decks
//	        JPanel decksPanel = new JPanel(new GridLayout(1, 2));
//
//	        // display study decks in the left panel
//	        studyDecksListModel = new DefaultListModel<>();
//	        for (Deck deck : studyPlan.selectedDecks) {
//	            studyDecksListModel.addElement(deck.getDeckTitle());
//	        }
//	        studyDecksList.setModel(studyDecksListModel);
//	        JScrollPane studyScrollPane = new JScrollPane(studyDecksList);
//	        JLabel studyDecksLabel = new JLabel("Study Decks");
//	        studyDecksLabel.setHorizontalAlignment(JLabel.CENTER);
//	        decksPanel.add(studyScrollPane);
//	        decksPanel.add(studyDecksLabel);
//
//	        // display repeat decks in the right panel
//	        repeatDecksListModel = new DefaultListModel<>();
//	        for (Deck deck : studyPlan.getAllRepeatDecks()) {
//	            repeatDecksListModel.addElement(deck.getDeckTitle());
//	        }
//	        repeatDecksList.setModel(repeatDecksListModel);
//	        JScrollPane repeatScrollPane = new JScrollPane(repeatDecksList);
//	        JLabel repeatDecksLabel = new JLabel("Repeat Decks");
//	        repeatDecksLabel.setHorizontalAlignment(JLabel.CENTER);
//	        decksPanel.add(repeatScrollPane);
//	        decksPanel.add(repeatDecksLabel);
//	        mainPanel.add(decksPanel);
//	        
//	        backButton = new JButton("Back");
//	        backButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					controller.landingPage();
//				}
//			});
//	        mainPanel.add(backButton);
//	        add(mainPanel);//, BorderLayout.CENTER);
//	        setSize(400, 400);
//	        setVisible(true);
//
//	    }
	    
	    public UserStudyPlannerPage(User user, ArrayList<StudyPlan> retrievedStudyPlan, Controller controller) {
	        this.user = user;
	        this.studyPlans = retrievedStudyPlan;
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

	        LocalDate date = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String formattedDate = date.format(formatter);
	        currentDateLabel = new JLabel("Current Date: " + formattedDate);
	        currentDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(currentDateLabel);
	        
	        studyPlanTitleLabel = new JLabel("Name of Study Plan: ");
	        testDateLabel = new JLabel("Test Date: ");
	        studyPlanTitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        testDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(studyPlanTitleLabel);
	        mainPanel.add(testDateLabel);
	        
//	        // Display list of study plans
//	        ArrayList<String> studyPlanTitles = new ArrayList<>();
//	        for (StudyPlan studyPlan : retrievedStudyPlan) {
//	            studyPlanTitles.add(studyPlan.getStudyPlanTitle());
//	        }
//	        studyPlanList = new JList<>(studyPlanTitles.toArray(new String[studyPlanTitles.size()]));
//	        studyPlanList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//	        JScrollPane studyPlanScrollPane = new JScrollPane(studyPlanList);
//	        studyPlanScrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
//	        mainPanel.add(studyPlanScrollPane);
//
//	        // Display button to select study plan and view its decks
//	        JButton viewDecksButton = new JButton("View Study Plan Decks");
//	        viewDecksButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
//	        viewDecksButton.addActionListener(e -> {
//	            String selectedStudyPlanTitle = studyPlanList.getSelectedValue();
//	            if (selectedStudyPlanTitle == null) {
//	                JOptionPane.showMessageDialog(null, "Please select a study plan!");
//	                return;
//	            }
//
//	            // Get selected study plan and display its decks
//	            StudyPlan selectedStudyPlan = null;
//	            for (StudyPlan studyPlan : retrievedStudyPlan) {
//	                if (studyPlan.getStudyPlanTitle().equals(selectedStudyPlanTitle)) {
//	                    selectedStudyPlan = studyPlan;
//	                    break;
//	                }
//	            }
//
//	            displayStudyDecksList(selectedStudyPlan.getSelectedDecks());
//	            displayRepeatDecksList(selectedStudyPlan.getAllRepeatDecks());
//	        });
//	        mainPanel.add(viewDecksButton);

//	        if (studyPlans != null) {
//	            ArrayList<String> studyPlanTitles = new ArrayList<>();
//	            for (StudyPlan studyPlan : studyPlans) {
//	                studyPlanTitles.add(studyPlan.getStudyPlanTitle());
//	            }
//	            studyPlanList = new JList<>(studyPlanTitles.toArray(new String[0]));
//	            JScrollPane studyPlanScrollPane = new JScrollPane(studyPlanList);
//	            studyPlanScrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
//	            mainPanel.add(studyPlanScrollPane);
//	        } else {
//	            JLabel noStudyPlansLabel = new JLabel("You don't have any study plans yet.");
//	            noStudyPlansLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//	            mainPanel.add(noStudyPlansLabel);
//	        }
//
//	        JButton selectButton = new JButton("Select Study Plan");
//	        selectButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
//	        selectButton.addActionListener(e -> {
//	            String selectedStudyPlanTitle = studyPlanList.getSelectedValue();
//	            if (selectedStudyPlanTitle == null) {
//	                JOptionPane.showMessageDialog(null, "Please select a study plan!");
//	                return;
//	            }
//	            for (StudyPlan studyPlan : studyPlans) {
//	                if (studyPlan.getStudyPlanTitle().equals(selectedStudyPlanTitle)) {
//	                    this.studyPlan = studyPlan;
//	                    break;
//	                }
//	            }
//	            displayStudyPlanDetails();
//	        });
//	        mainPanel.add(selectButton);
	        
	        JButton allStudyPlansButton = new JButton("All Study Plans");
	        allStudyPlansButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        if (retrievedStudyPlan != null) {
	        allStudyPlansButton.addActionListener(e -> {
	            ArrayList<StudyPlan> studyPlanList = mysql_database.getAllStudyPlansForUser(user.getUsername());
	            displayStudyPlanList(studyPlanList);
	        });
	        } else {
	        	JOptionPane.showMessageDialog(null, "You don't have any study plans yet. Please create one");
                return;
        	}
	        mainPanel.add(allStudyPlansButton);

	        JList studyPlanList = new JList<>();
	        studyPlanScrollPane = new JScrollPane(studyPlanList);
	        //mainPanel.add(studyPlanScrollPane);

	        JButton allDecksButton = new JButton("View Study Plan Decks");
	        allDecksButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        allDecksButton.addActionListener(e -> {
	            StudyPlan selectedStudyPlan = (StudyPlan) studyPlanList.getSelectedValue();
	            if (selectedStudyPlan == null) {
	                JOptionPane.showMessageDialog(null, "Please select a study plan to view decks!");
	                return;
	            }
	            displayStudyDecksList(selectedStudyPlan.selectedDecks);
	        });
	        
	        quizButton = new JButton("Start Quiz Session");
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
	        
	        backButton = new JButton("Back");
	        backButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                controller.landingPage();
	            }
	        });
	        
	        Box buttonBox = Box.createHorizontalBox();
	        buttonBox.add(backButton);
	        buttonBox.add(allDecksButton);
	        buttonBox.add(quizButton);
	        mainPanel.add(buttonBox);
	        //mainPanel.add(allDecksButton);
	        
	        studyDecksList = new JList<>();
	        repeatDecksList = new JList<>();
	        JScrollPane studyDeckScrollPane = new JScrollPane(studyDecksList);
	        JPanel listPanel = new JPanel();
	        listPanel.add(studyDeckScrollPane);
	        mainPanel.add(listPanel);
	        
	        add(mainPanel);
	        setSize(400, 400);
	        setVisible(true);
	    }

	    private void displayStudyPlanList(ArrayList<StudyPlan> studyPlans) {
	    	if (studyPlans != null) {
	        DefaultListModel<String> planListModel = new DefaultListModel<>();
	        for (StudyPlan plan : studyPlans) {
	            planListModel.addElement(plan.getStudyPlanTitle());
	        }
	        
	        JList<String> planList = new JList<>(planListModel);
	        JScrollPane planScrollPane = new JScrollPane(planList);
	        JPanel planPanel = new JPanel(new BorderLayout());
	        planPanel.setBorder(BorderFactory.createTitledBorder("Select a Study Plan"));
	        planPanel.add(planScrollPane);

	        planList.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                if (evt.getClickCount() == 1) {
	                    String selectedPlanTitle = planList.getSelectedValue();
	                    if (selectedPlanTitle != null) {
	                        studyPlan = mysql_database.getStudyPlanByTitle(selectedPlanTitle);
	                        displayStudyDecksList(studyPlan.getSelectedDecks());
	                    }
	                }
	            }
	        });
	    	
	        studyPlanScrollPane.setViewportView(planPanel);
	        mainPanel.add(studyPlanScrollPane);
	        revalidate();
	    	 } else {
	    		 JOptionPane.showMessageDialog(null, "You don't have any study plans yet. Please create one");
	             return;
	    	 }
	    }

	    
	    private void displayStudyPlanDetails() {
	        mainPanel.removeAll();
	        titleLabel.setText("Study Plan Details");
	        studyPlanTitleLabel = new JLabel("Name of Study Plan: " + studyPlan.getStudyPlanTitle());
	        testDateLabel = new JLabel("Test Date: " + studyPlan.getTestDate());
	        studyPlanTitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        testDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(studyPlanTitleLabel);
	        mainPanel.add(testDateLabel);

	        LocalDate date = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String formattedDate = date.format(formatter);
	        currentDateLabel = new JLabel("Current Date: " + formattedDate);
	        currentDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        mainPanel.add(currentDateLabel);

	        // Add a button to display all study plans
	        JButton allStudyPlansButton = new JButton("All Study Plans");
	        allStudyPlansButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        allStudyPlansButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Get all study plans for the current user
	                ArrayList<StudyPlan> studyPlanList = mysql_database.getAllStudyPlansForUser(user.getUsername());
	                
	                // Display the list of study plans
	                displayStudyPlanList(studyPlanList);
	            }
	        });
	        mainPanel.add(allStudyPlansButton);

	        allButton = new JButton("All Study Decks");
	        allButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	        allButton.addActionListener(e -> {
	            // Get the selected study plan
//	        	StudyPlan selectedStudyPlan = studyPlanList.get(studyPlanList.getSelectedIndex());
//	            
//	            // Display the selected study plan's decks
//	            displayStudyDecksList(selectedStudyPlan.selectedDecks);
//	            displayRepeatDecksList(selectedStudyPlan.getAllRepeatDecks());
	        });
	        allButton.setEnabled(false);
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
	        quizButton.setEnabled(false);
	        mainPanel.add(quizButton);

	        backButton = new JButton("Back");
	        backButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                controller.landingPage();
	            }
	        });
	        mainPanel.add(backButton);
	        add(mainPanel);
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
	                        Deck selectedDeck = mysql_database.getDeckByTitle(selectedDeckTitle);
	                        controller.quiz(selectedDeck);
	                    }
	                }
	            }
	        });
	    }

}