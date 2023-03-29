package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import Models.Flashcard;

public class FlashcardPanel extends JPanel implements ActionListener {

	private JTextArea flashcardQuestion = new JTextArea();
	private JTextArea flashcardAnswer = new JTextArea();
	private JButton deleteFlashcard = new JButton("Delete");
	private JButton saveFlashcard = new JButton("Save");
	private ArrayList<Flashcard> flashcards;
	private Controller controller;
	private Flashcard flashcard;
	private String deckID;
	/**
	 * Create the panel.
	 */
	
	
	
	public FlashcardPanel(ArrayList<Flashcard> flashcards, Controller controller, String deckID) {
		this.flashcards = flashcards;
		this.controller = controller;
		this.deckID = deckID;
		initialize();
	}
	
	private void initialize() {
		//System.out.println("FLASHCARD");
		this.setBackground(Color.WHITE);
		//this.setBounds(10, 197, 717, 113);
		setPreferredSize(new Dimension(718, 110));
		
		this.setLayout(null);
		
	
		flashcardQuestion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		flashcardQuestion.setBackground(Color.WHITE);
		flashcardQuestion.setBounds(10, 11, 347, 54);
		this.add(flashcardQuestion);
		

		flashcardAnswer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		flashcardAnswer.setBackground(Color.WHITE);
		flashcardAnswer.setBounds(373, 11, 332, 54);
		this.add(flashcardAnswer);
		deleteFlashcard.setForeground(new Color(255, 255, 255));
		deleteFlashcard.setBackground(new Color(0, 0, 0));
		
		
		deleteFlashcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashcards.remove(flashcard);
				setBackground(Color.WHITE);
				setVisible(false);
			}
		});
		deleteFlashcard.setBounds(517, 78, 89, 23);
		this.add(deleteFlashcard);
		saveFlashcard.setForeground(new Color(255, 255, 255));
		saveFlashcard.setBackground(new Color(0, 0, 0));
		saveFlashcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashcard = controller.createFlashcard(flashcardQuestion.getText(), flashcardAnswer.getText(), deckID);
				flashcards.add(flashcard);
				setBackground(new Color(51, 204, 255));
				
			}
		});
		
		
		saveFlashcard.setBounds(616, 78, 89, 23);
		this.add(saveFlashcard);
		
		setSize(717, 113);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
