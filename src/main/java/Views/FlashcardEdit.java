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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FlashcardEdit extends JPanel implements ActionListener {

	private JTextArea flashcardQuestion = new JTextArea();
	private JTextArea flashcardAnswer = new JTextArea();
	private JButton deleteFlashcard = new JButton("Delete");
	private JButton saveFlashcard = new JButton("Save");
	private JPanel currentPanel;
	JButton btnAutoComplete = new JButton("Auto Complete");
	private ArrayList<Flashcard> flashcards;
	private Controller controller;
	private Flashcard flashcard;
	private String deckID;
	private int flashcardIndex;
	/**
	 * Create the panel.
	 */
	
	
	
	// New Flashcard
	public FlashcardEdit(ArrayList<Flashcard> flashcards, int flashcardIndex, Controller controller, String deckID) {
		this.flashcards = flashcards;
		this.flashcardIndex = flashcardIndex;
		this.controller = controller;
		this.deckID = deckID;
		this.currentPanel = this;
		initialize();
	}
	
	private void initialize() {
		this.checkQuestionEmpty();
		//System.out.println("FLASHCARD");
		this.setBackground(Color.WHITE);
		//this.setBounds(10, 197, 717, 113);
		setPreferredSize(new Dimension(718, 110));
		
		this.setLayout(null);
		flashcardQuestion.addKeyListener(new KeyAdapter() {
			int i = 0;
			
			@Override
			public void keyTyped(KeyEvent e) {
				checkQuestionEmpty();
			}
		});
		
	
		flashcardQuestion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		flashcardQuestion.setBackground(Color.WHITE);
		flashcardQuestion.setBounds(10, 11, 347, 54);
		flashcardQuestion.setText(flashcards.get(flashcardIndex).getQuestion());
		this.add(flashcardQuestion);
		

		flashcardAnswer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		flashcardAnswer.setBackground(Color.WHITE);
		flashcardAnswer.setBounds(373, 11, 332, 54);
		flashcardAnswer.setLineWrap(true);
		flashcardAnswer.setWrapStyleWord(true);
		flashcardAnswer.setText(flashcards.get(flashcardIndex).getAnswer());
		this.add(flashcardAnswer);
		deleteFlashcard.setForeground(new Color(255, 255, 255));
		deleteFlashcard.setBackground(new Color(0, 0, 0));
		
		
		deleteFlashcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteFlashcard(flashcards.get(flashcardIndex));
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
				flashcards.get(flashcardIndex).setQuestion(flashcardQuestion.getText().trim());
				flashcards.get(flashcardIndex).setAnswer(flashcardAnswer.getText().trim());
				controller.updateFlashcard(flashcards.get(flashcardIndex));
				//flashcard = controller.createFlashcard(flashcardQuestion.getText().trim(), flashcardAnswer.getText().trim(), deckID);
				//flashcards.add(flashcard);
				setBackground(new Color(51, 204, 255));
				
			}
		});
		
		
		saveFlashcard.setBounds(616, 78, 89, 23);
		this.add(saveFlashcard);
		
		setSize(717, 113);
		
		
		btnAutoComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String autoCompletedAnswer = controller.autoComplete(flashcardQuestion.getText(), "");
				flashcardAnswer.setText(autoCompletedAnswer);
				currentPanel.revalidate();
				currentPanel.repaint();
			}
		});
		
		
		
		btnAutoComplete.setForeground(Color.WHITE);
		btnAutoComplete.setBackground(Color.BLACK);
		btnAutoComplete.setBounds(383, 78, 122, 23);
		add(btnAutoComplete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void checkQuestionEmpty() {
		if (flashcardQuestion.getText().trim().isEmpty()) {
			btnAutoComplete.setEnabled(false);
		} else {
			btnAutoComplete.setEnabled(true);
		}
	}
}
