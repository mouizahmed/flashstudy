package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import Models.Deck;
import Models.Flashcard;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;

public class FlashcardView extends JPanel implements ActionListener {
	private ArrayList<Flashcard> flashcards;
	private Controller controller;
	private Flashcard flashcard;
	private Deck deck;
	private final JPanel flashcardQuestion = new JPanel();
	private final JPanel flashcardAnswer = new JPanel();
	private JLabel questionLabel;
	private JLabel answerLabel;
	private final JRadioButton hardButton = new JRadioButton("Hard");
	private final JRadioButton mediumButton = new JRadioButton("Medium");
	private final JRadioButton easyButton = new JRadioButton("Easy");
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	
	
	
	public FlashcardView(Deck deck, Flashcard flashcard, Controller controller) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		this.controller = controller;
		this.flashcard = flashcard;
		questionLabel = new JLabel(flashcard.question.trim());
		
		answerLabel = new JLabel(flashcard.answer.trim());
		this.deck = deck;
		initialize();
	}
	
	private void initialize() {
		//System.out.println("FLASHCARD");
		
		
		if (flashcard.getDifficultyColour().equals("Easy") && deck.createdBy.equals(controller.getCurrentUser().getUsername())) {
			this.setBackground(Color.green);
		} else if (flashcard.getDifficultyColour().equals("Medium") && deck.createdBy.equals(controller.getCurrentUser().getUsername())) {
			this.setBackground(Color.yellow);
		} else if (flashcard.getDifficultyColour().equals("Hard") && deck.createdBy.equals(controller.getCurrentUser().getUsername())) {
			this.setBackground(Color.red);
		} else {
			this.setBackground(Color.WHITE);
		}
		
		//this.setBounds(10, 197, 717, 113);
		setPreferredSize(new Dimension(718, 151));
		
		this.setLayout(null);
		
		setSize(717, 113);
		flashcardQuestion.setBackground(new Color(255, 255, 255));
		flashcardQuestion.setBounds(10, 11, 347, 88);
		flashcardQuestion.setLayout(new BoxLayout(flashcardQuestion, BoxLayout.X_AXIS));
		flashcardQuestion.add(questionLabel);
		
		add(flashcardQuestion);
		flashcardAnswer.setBackground(new Color(255, 255, 255));
		flashcardAnswer.setBounds(373, 11, 332, 88);
		flashcardAnswer.setLayout(new BoxLayout(flashcardAnswer, BoxLayout.X_AXIS));
		flashcardAnswer.add(answerLabel);
		
		add(flashcardAnswer);
		
		JButton btnNewButton = new JButton("Fact Check");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.verifyFlashcard(deck, flashcard);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 113, 98, 26);
		add(btnNewButton);
		
		if (this.deck.createdBy.equals(controller.getCurrentUser().getUsername())) {
			buttonGroup.add(hardButton);
			hardButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setBackground(Color.red);
					controller.updateFlashcardDifficulty(flashcard, "Hard");
					
				}
			});
			hardButton.setBounds(653, 118, 52, 24);
			
			add(hardButton);
			buttonGroup.add(mediumButton);
			mediumButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setBackground(Color.yellow);
					controller.updateFlashcardDifficulty(flashcard, "Medium");
				}
			});
			mediumButton.setBounds(579, 118, 70, 24);
			
			add(mediumButton);
			buttonGroup.add(easyButton);
			easyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setBackground(Color.green);
					controller.updateFlashcardDifficulty(flashcard, "Easy");
				}
			});
			easyButton.setBounds(523, 118, 52, 24);
			
			add(easyButton);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
