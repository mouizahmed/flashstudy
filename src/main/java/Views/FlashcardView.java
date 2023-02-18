package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import Models.Deck;
import Models.Flashcard;

public class FlashcardView extends JPanel implements ActionListener {
	private ArrayList<Flashcard> flashcards;
	private Controller controller;
	private Flashcard flashcard;
	private final JPanel flashcardQuestion = new JPanel();
	private final JPanel flashcardAnswer = new JPanel();
	private JLabel questionLabel;
	private JLabel answerLabel;

	/**
	 * Create the panel.
	 */
	
	
	
	public FlashcardView(Flashcard flashcard, Controller controller) {
		this.controller = controller;
		this.flashcard = flashcard;
		questionLabel = new JLabel(flashcard.question);
		answerLabel = new JLabel(flashcard.answer);
		initialize();
	}
	
	private void initialize() {
		//System.out.println("FLASHCARD");
		this.setBackground(Color.WHITE);
		//this.setBounds(10, 197, 717, 113);
		setPreferredSize(new Dimension(718, 110));
		
		this.setLayout(null);
		
		setSize(717, 113);
		flashcardQuestion.setBounds(10, 11, 347, 88);
		flashcardQuestion.add(questionLabel);
		
		add(flashcardQuestion);
		flashcardAnswer.setBounds(373, 11, 332, 88);
		flashcardAnswer.add(answerLabel);
		
		add(flashcardAnswer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
