package Views;

import javax.swing.JFrame;

import Controller.Controller;
import Models.Deck;
import Models.Flashcard;
import Models.Verdict;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FactCheck extends JFrame {
	
	private Deck deck;
	private Flashcard flashcard;
	private Controller controller;
	private Verdict verdict;
	
	public FactCheck(Deck deck, Flashcard flashcard, Controller controller, Verdict verdict) {
		this.deck = deck;
		this.flashcard = flashcard;
		this.controller = controller;
		this.verdict = verdict;
		initialize();
	}
	
	
	private void initialize() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		
		JLabel lblNewLabel = new JLabel("Verdict: " + verdict.getVerdict());
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(lblNewLabel);
		
		if (verdict.getVerdict() == false) {
			JLabel lblNewLabel_1 = new JLabel("Correct Definition: " + verdict.getCorrection());
			lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
			getContentPane().add(lblNewLabel_1);
			
			if (deck.getCreatedBy().equals(controller.getCurrentUser().getUsername())) {
				JButton btnNewButton = new JButton("Do you want to update your definition?");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.updateFlashcard(deck, flashcard, verdict.getCorrection());
					}
				});
				btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				getContentPane().add(btnNewButton);
			}
		}
		setMinimumSize(new Dimension(500, 200));
	}
	
	
	
	

}
