package Views;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import Controller.Controller;
import Models.Deck;

public class OpenDeckPage extends JPanel {
	
	JPanel panel = new JPanel();
	private JPanel dynamicPanel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	JPanel panel_2 = new JPanel();
	private Deck deck;
	private Controller controller;
	private JLabel title;
	
	/**
	 * Create the panel.
	 */
	public OpenDeckPage(Deck deck, Controller controller) {
		this.deck = deck;
		this.controller = controller;
		initialize();
	}
	
	private void initialize() {
		setPreferredSize(new Dimension(750, 500));
		setLayout(null);
		
		
		panel.setLayout(null);
		panel.setBounds(0, 0, 738, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.browse();
			}
		});
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(459, 59, 267, 26);
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton btnNewButton_1 = new JButton("Edit Deck");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Create Session");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.session(deck.getAllFlashcards());
			}
		});
		panel_1.add(btnNewButton_2);
		
		JButton Quiz = new JButton("Quiz");
		Quiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.quiz(deck);
			}
		});
		panel_1.add(Quiz);
		
		
		panel_2.setPreferredSize(new Dimension(738, 403));
		panel_2.setBounds(0, 97, 738, 403);
		add(panel_2);
		
		
		scrollPane.setPreferredSize(new Dimension(738, 403));
		panel_2.add(scrollPane);
		
		
		dynamicPanel.setPreferredSize(new Dimension(738, 400));
	
		
		for (int i = 0; i < deck.getAllFlashcards().size(); i++) {
			//JLabel deckTitle = new JLabel(deck.get(i).getDeckTitle());
//			JLabel deckCreatedBy = new JLabel("Created by: " + publicDecks.get(i).createdBy);
//			JPanel deckPanel = new JPanel();
//			deckPanel.setPreferredSize(new Dimension(150, 50));
//			deckPanel.setBackground(Color.YELLOW);
//			deckPanel.add(deckTitle);
//			deckPanel.add(deckCreatedBy);
			
			FlashcardView flashcardView = new FlashcardView(deck.getAllFlashcards().get(i), controller);
			
			dynamicPanel.add(flashcardView);
			
			dynamicPanel.revalidate();
			dynamicPanel.repaint();
			
			
			
		}
		
		
		scrollPane.setViewportView(dynamicPanel);
		
		title = new JLabel(deck.getDeckTitle());
		title.setBounds(12, 69, 62, 16);
		add(title);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
