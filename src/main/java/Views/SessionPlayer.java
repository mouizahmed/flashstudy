package Views;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Models.Flashcard;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SessionPlayer extends JPanel {

	private JPanel flashcardPanel = new JPanel();
	private int index = 0;
	private JLabel text;
	private ArrayList<Flashcard> flashcards;
	private Controller controller;
	/**
	 * Create the panel.
	 */
	public SessionPlayer(ArrayList<Flashcard> flashcards, Controller controller) {
		setBackground(new Color(255, 255, 255));
		this.flashcards = flashcards;
		this.controller = controller;
		initialize();
	}
	
	private void initialize() {
		setPreferredSize(new Dimension(750, 500));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setBounds(0, 0, 738, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.previous();
			}
		});
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.profilePage(controller.getCurrentUser());
			}
		});
		profileButton.setForeground(new Color(255, 255, 255));
		profileButton.setBackground(new Color(0, 0, 0));
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		
		flashcardPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		flashcardPanel.setBackground(Color.WHITE);
		flashcardPanel.setToolTipText("Click to reveal");
		flashcardPanel.setBounds(120, 137, 482, 193);
		
		text = new JLabel(flashcards.get(index).question);
		flashcardPanel.add(text);
		add(flashcardPanel);
		
		JButton revealAnswer = new JButton("Reveal");
		revealAnswer.setForeground(new Color(255, 255, 255));
		revealAnswer.setBackground(new Color(0, 0, 0));
		revealAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashcardPanel.remove(text);
				text = new JLabel(flashcards.get(index).answer);
				flashcardPanel.add(text);
				flashcardPanel.setBackground(Color.LIGHT_GRAY);
				flashcardPanel.revalidate();
				flashcardPanel.repaint();
				revealAnswer.setEnabled(false);
			}
		});
		revealAnswer.setBounds(248, 340, 98, 26);
		add(revealAnswer);

		JButton nextQuestion = new JButton("Next");
		nextQuestion.setForeground(new Color(255, 255, 255));
		nextQuestion.setBackground(new Color(0, 0, 0));
		nextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index += 1;
				flashcardPanel.setBackground(Color.WHITE);
				flashcardPanel.remove(text);
				text = new JLabel(flashcards.get(index).question);
				flashcardPanel.add(text);
				
				flashcardPanel.revalidate();
				flashcardPanel.repaint();
				revealAnswer.setEnabled(true);
				checkLast();
			}

			private void checkLast() {
				// TODO Auto-generated method stub
				if (index+1 >= flashcards.size()) {
					nextQuestion.setEnabled(false);
				}
				
			}
		});
		
		
		
		nextQuestion.setBounds(356, 340, 98, 26);
		add(nextQuestion);
		
	}
}
