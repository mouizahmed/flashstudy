package Views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import Controller.Controller;
import Models.Deck;
import Models.User;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UserView extends JPanel {
	private JTextField textField;
	private JPanel dynamicPanel = new JPanel(new FlowLayout());
	private JPanel resultsContainer = new JPanel();
//	private ArrayList<Deck> publicDecks = new ArrayList<>();
	private ArrayList<Deck> searchedDecks = new ArrayList<>();
	private Controller controller;
	private User user;
	/**
	 * Create the panel.
	 */
	public UserView(User user, Controller controller) {
		setBackground(new Color(255, 255, 255));
		this.controller = controller;
		this.user = user;
		
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
				controller.landingPage();
			}
		});
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setForeground(new Color(255, 255, 255));
		profileButton.setBackground(new Color(0, 0, 0));
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.profilePage(controller.getCurrentUser());
			}
		});
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 58, 738, 111);
		add(panel_1);
		panel_1.setLayout(null);
		
		populatePage(user.getUserDeckList());
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedDecks = controller.searchUserDecks(textField.getText());
				populatePage(searchedDecks);
				System.out.println("ENTER");
			}
		});
		textField.setBounds(186, 51, 367, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(186, 14, 367, 26);
		panel_1.add(panel_2);
		
		JLabel pageTitle = new JLabel(user.getUsername() + " | " + user.getEmail());
		panel_2.add(pageTitle);
		resultsContainer.setBackground(new Color(255, 255, 255));
		
		
		resultsContainer.setBounds(0, 197, 738, 303);
		add(resultsContainer);
		dynamicPanel.setBackground(new Color(255, 255, 255));
		
		dynamicPanel.setPreferredSize(new Dimension(150, 500));
		
		JScrollPane scrollPane = new JScrollPane(dynamicPanel);
		scrollPane.setMinimumSize(new Dimension(720, 303));
		scrollPane.setPreferredSize(new Dimension(728, 303));
		resultsContainer.add(scrollPane);

		
	}
	
	
	private void populatePage(ArrayList<Deck> decks) {
		dynamicPanel.removeAll();
		for (int i = 0; i < decks.size(); i++) {
			JLabel deckTitle = new JLabel(decks.get(i).getDeckTitle());
			JLabel deckCreatedBy = new JLabel("Created by: " + decks.get(i).createdBy);
			JPanel deckPanel = new JPanel();
			deckPanel.setPreferredSize(new Dimension(150, 50));
			deckPanel.setBackground(Color.BLACK);
			deckTitle.setForeground(Color.WHITE);
			deckCreatedBy.setForeground(Color.WHITE);
			deckPanel.add(deckTitle);
			deckPanel.add(deckCreatedBy);
			Deck current = decks.get(i);
			dynamicPanel.add(deckPanel);
			
			deckPanel.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent me) {
					controller.deckPage(current);
				}
				
			});
			
			dynamicPanel.revalidate();
			dynamicPanel.repaint();
			
			
			
		}
	
			
		dynamicPanel.revalidate();
		dynamicPanel.repaint();
	}
}
