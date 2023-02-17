package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import Models.Flashcard;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Component;

public class CreateDeckPage extends JPanel {
	private JTextField deckTitle;
	private JTextField school;
	private JTextField faculty;
	private JTextField course;
	private Controller controller;
	private boolean publicity = true;
	private ArrayList<Flashcard> flashcards;
	private int flashcardNum = 0;
	private int initialDynamicSize = 500;
	private JPanel flashcardContainer = new JPanel();
	private JPanel dynamicPanel = new JPanel(new FlowLayout());

	/**
	 * Create the panel.
	 */
	public CreateDeckPage(Controller controller) {
		setMinimumSize(new Dimension(750, 500));
		setSize(new Dimension(750, 500));
		setPreferredSize(new Dimension(750, 500));
		this.controller = controller;
		flashcards = new ArrayList<>();
		initialize();

	}
	
	private void initialize() {
		setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 738, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.landingPage();
			}
		});
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 47, 738, 139);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel title = new JLabel("Create a new study set");
		title.setBounds(10, 11, 130, 16);
		panel_1.add(title);
		
		deckTitle = new JTextField();
		deckTitle.setText("Title*");
		deckTitle.setBounds(10, 36, 363, 20);
		panel_1.add(deckTitle);
		deckTitle.setColumns(10);
		
		JTextArea deckDescription = new JTextArea();
		deckDescription.setText("Description");
		deckDescription.setBounds(10, 67, 363, 53);
		panel_1.add(deckDescription);
		
		school = new JTextField();
		school.setText("School");
		school.setColumns(10);
		school.setBounds(383, 36, 343, 20);
		panel_1.add(school);
		
		faculty = new JTextField();
		faculty.setText("Faculty");
		faculty.setColumns(10);
		faculty.setBounds(383, 69, 343, 20);
		panel_1.add(faculty);
		
		course = new JTextField();
		course.setText("Course");
		course.setColumns(10);
		course.setBounds(383, 100, 343, 20);
		panel_1.add(course);
		
		JRadioButton publicDeck = new JRadioButton("Public");
		publicDeck.setSelected(true);
		publicDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (publicDeck.isSelected()) {
					publicity = true;
				}
			}
		});
		publicDeck.setBounds(383, 11, 60, 24);
		panel_1.add(publicDeck);
		
		JRadioButton privateDeck = new JRadioButton("Private");
		privateDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (privateDeck.isSelected()) {
					publicity = false;
				}
			}
		});
		privateDeck.setBounds(447, 11, 65, 24);
		panel_1.add(privateDeck);
		
		ButtonGroup privacy = new ButtonGroup();
		privacy.add(publicDeck);
		privacy.add(privateDeck);
		flashcardContainer.setMaximumSize(new Dimension(728, 32767));
		flashcardContainer.setPreferredSize(new Dimension(220, 150));
		
		/// FLASHCARD PLACEHOLDER
		
		flashcardContainer.setBounds(0, 198, 738, 120);
		add(flashcardContainer);
		
		dynamicPanel.setPreferredSize(new Dimension(150, 500));
		
		JScrollPane scrollPane = new JScrollPane(dynamicPanel);
		scrollPane.setMinimumSize(new Dimension(720, 110));
		scrollPane.setPreferredSize(new Dimension(728, 150));
		flashcardContainer.add(scrollPane);
		
		
		
		JButton btnCreateDeck = new JButton("Create Deck");
		btnCreateDeck.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCreateDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createDeck(deckTitle.getText(), flashcards, publicity);
			}
		});
		btnCreateDeck.setBounds(316, 435, 104, 26);
		add(btnCreateDeck);
		
		JButton addFlashcard = new JButton("Add Card");
		addFlashcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFlashcard();
			}
		});
		addFlashcard.setBounds(12, 346, 695, 77);
		add(addFlashcard);
		
		
	}
	

	
	private void addFlashcard() {
		System.out.println("add");
		flashcardNum += 1;
		System.out.println(flashcards.size());
		if (flashcardNum % 3 == 0) {
			
			dynamicPanel.setPreferredSize(new Dimension(150, initialDynamicSize += 500));
		}
		FlashcardPanel flashcardPanel = new FlashcardPanel(flashcards, controller);
		dynamicPanel.add(flashcardPanel);
		//JLabel someLabel = new JLabel("Some new Label");
		//flashcardContainer.add(someLabel);
		
		 //JPanel newPanel = new JPanel();
         //newPanel.setPreferredSize(new Dimension(50, 50));
         //newPanel.setBackground(Color.YELLOW);
         
         //flashcardContainer.add(flashcardPanel);
		
		dynamicPanel.revalidate();
		dynamicPanel.repaint();
		
//		revalidate();
//		repaint();
		
	}
}
