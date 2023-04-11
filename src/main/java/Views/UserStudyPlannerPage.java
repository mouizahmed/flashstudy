package Views;

import java.util.ArrayList;
import java.util.Date;

import Models.Deck;
import Models.JDBC;
import Models.StudyPlan;
import Models.User;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import Controller.Controller;

public class UserStudyPlannerPage extends JPanel {
	public ArrayList<StudyPlan> studyPlans;
	public StudyPlan studyPlan;
	public JPanel mainPanel;
	public JLabel currentDateLabel;
	public JButton quizButton;
	public JList<String> studyDecksList;
	public JButton backButton;
	public User user;
	public Controller controller;
	public JDBC mysql_database;
	public JScrollPane studyPlanScrollPane;
	public JPanel listPanel;
	public JButton deleteButton;

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

		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel studyPlanLabel = new JLabel("Your Study Plan");
		studyPlanLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(studyPlanLabel, BorderLayout.NORTH);

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = date.format(formatter);
		currentDateLabel = new JLabel("Current Date: " + formattedDate);
		currentDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		currentDateLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(currentDateLabel, BorderLayout.CENTER);
		mainPanel.add(topPanel, BorderLayout.CENTER);

		JButton allStudyPlansButton = new JButton("Get All Your Study Plans");
		allStudyPlansButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

		allStudyPlansButton.addActionListener(e -> {
			ArrayList<StudyPlan> studyPlanList = mysql_database.getAllStudyPlansForUser(user.getUsername());
			if (studyPlanList.isEmpty()) {
				JOptionPane.showMessageDialog(null, "You don't have any study plans yet. Please create one!");
				return;
			} else {
				displayStudyPlanList(studyPlanList, studyPlanLabel);
			}
		});
		mainPanel.add(allStudyPlansButton);

		JList studyPlanList = new JList<>();
		studyPlanScrollPane = new JScrollPane(studyPlanList);

		quizButton = new JButton("Start Quiz Session for Deck");
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
		buttonBox.add(quizButton);
		mainPanel.add(buttonBox);

		studyDecksList = new JList<>();
		JScrollPane studyDeckScrollPane = new JScrollPane(studyDecksList);
		JLabel AllDecksLabel = new JLabel("All Decks for Study Plan:");
		listPanel = new JPanel();
		deleteButton = new JButton("Delete Deck for Study Plan");
		listPanel.add(AllDecksLabel, BorderLayout.CENTER);
		listPanel.add(studyDeckScrollPane);
		listPanel.add(deleteButton, BorderLayout.EAST); 
		mainPanel.add(listPanel);

		add(mainPanel);
		setVisible(true);
	}

	private void displayStudyPlanList(ArrayList<StudyPlan> studyPlans, JLabel studyPlanLabel) {
		DefaultListModel<String> planListModel = new DefaultListModel<>();
		for (StudyPlan plan : studyPlans) {
			planListModel.addElement(plan.getStudyPlanTitle());
		}

		JList<String> planList = new JList<>(planListModel);
		JScrollPane planScrollPane = new JScrollPane(planList);
		JPanel planPanel = new JPanel(new BorderLayout());
		planPanel.setBorder(BorderFactory.createTitledBorder("Select a Study Plan"));
		planPanel.add(planScrollPane);

		JButton deleteButton = new JButton("Delete Study Plan");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedPlanTitle = planList.getSelectedValue();
				if (selectedPlanTitle != null) {
					StudyPlan selectedPlan = mysql_database.getStudyPlanByTitle(selectedPlanTitle);
					String testDateString = selectedPlan.getTestDate();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date testDate = null;
					try {
						testDate = dateFormat.parse(testDateString);
					} catch (ParseException ex) {
						ex.printStackTrace();
					}

					Date currentDate = new Date();
					if (testDate == null || currentDate.after(testDate)) {
						int selectedOption = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to delete this study plan: \"" + selectedPlanTitle + "\" ?",
								"Confirmation", JOptionPane.YES_NO_OPTION);
						if (selectedOption == JOptionPane.YES_OPTION) {
							mysql_database.deleteStudyPlan(selectedPlanTitle, user.getUsername());
							ArrayList<StudyPlan> updatedPlans = mysql_database.getAllStudyPlansForUser(user.getUsername());
							displayStudyPlanList(updatedPlans, studyPlanLabel);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"You can only delete study plan if the test date is today or has passed.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(deleteButton);
		planPanel.add(buttonPanel, BorderLayout.SOUTH);

		planList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					String selectedPlanTitle = planList.getSelectedValue();
					if (selectedPlanTitle != null) {
						studyPlan = mysql_database.getStudyPlanByTitle(selectedPlanTitle);
						displayStudyDecksList(studyPlan.getSelectedDecks());
						// Update the study plan label with the selected study plan's title and test date
						studyPlanLabel.setText("<html>Your Study Plan: " + studyPlan.getStudyPlanTitle() + "<br>Test Date: " + studyPlan.getTestDate() + "</html>");
					}
				}
			}
		});

		studyPlanScrollPane.setViewportView(planPanel);
		mainPanel.add(studyPlanScrollPane);
		revalidate();

	}

	private void displayStudyDecksList(ArrayList<Deck> studyDecks) {
		if (studyDecks.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You don't have any decks for this Study Plan.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultListModel<String> studyListModel = new DefaultListModel<>();

		for (Deck deck : studyDecks) {
			if (deck == null) {
				JOptionPane.showMessageDialog(null, "You don't have any decks for this Study Plan.", "Error", JOptionPane.ERROR_MESSAGE);
				studyDecksList.setModel(studyListModel);
				return;
			} else {
				studyListModel.addElement(deck.getDeckTitle());
			}
		}

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = studyDecksList.getSelectedIndex();
				if (selectedIndex == -1 || selectedIndex >= studyListModel.getSize()) {
					JOptionPane.showMessageDialog(null, "Please select a valid deck to delete.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String selectedDeckTitle = studyListModel.get(selectedIndex);
				studyDecksList.setSelectedValue(selectedDeckTitle, true);
				Deck selectedDeck = null;
				for (Deck deck : studyPlan.getSelectedDecks()) {
					if (deck.getDeckTitle().equals(selectedDeckTitle)) {
						selectedDeck = deck;
						break;
					}
				}
				if (selectedDeck == null) {
					JOptionPane.showMessageDialog(null, "Selected deck not found in study plan.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int selectedOption = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete the \"" + selectedDeckTitle + "\" deck?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
					// Check if the average score is 1
					String deckID = selectedDeck.getDeckID();
					double avgScore = mysql_database.getAvgScore(deckID, user.getUsername());

					if (avgScore == 1) {
						// Delete the selected deck from the study plan and update the display
						mysql_database.deleteSelectedDeckFromStudyPlan(studyPlan, selectedDeckTitle);
						studyDecksList.clearSelection();
						studyListModel.remove(selectedIndex);
						// Show success message
						JOptionPane.showMessageDialog(null, "The \"" + selectedDeckTitle + "\" deck has been deleted from the study plan.", "Success", JOptionPane.INFORMATION_MESSAGE);

						// Update the list of selected decks in the study plan
						displayStudyDecksList(studyPlan.getSelectedDecks());
						return;
					} else {
					JOptionPane.showMessageDialog(null, "This deck cannot be deleted as it has an average score of " + avgScore + ". You need to study this deck and get 100%", "Error", JOptionPane.ERROR_MESSAGE);
					return;
					}
				}
			}
		});

		// Add a mouse listener to the deck list
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