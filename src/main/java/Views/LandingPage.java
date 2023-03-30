package Views;

import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Models.Deck;
import Models.User;

public class LandingPage extends JPanel implements ActionListener {
	private ArrayList<Deck> publicDecks = new ArrayList<>();
	//private ArrayList<Deck> publicDecks = new ArrayList<>();
	private Controller controller;
	
	String username;
	JButton add = new JButton("New Deck");
	JButton browse = new JButton("Browse");
	JButton logout = new JButton("Logout");
	JButton SPButton = new JButton("Your Study Plan");
	JButton new_SPButton = new JButton("Create New Study Plan");
	JPanel buttons = new JPanel();
	JPanel title = new JPanel();
	
	JLabel titleMsg;
	
	JPanel main;
	CardLayout card;
	
	public LandingPage(Controller controller) {
		
		this.controller = controller;
//		this.main = main;
//		this.card = card;
//		this.username = username;
		
		this.add(title);
		this.add(buttons);
		initialize();
	}
	
	private void initialize() {
		titleMsg = new JLabel("Welcome " + username + "!");
		title.add(titleMsg);
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.browse();
			}
		});
		
		buttons.add(browse);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createDeckPage();
			}
		});
		buttons.add(add);
		
		SPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.profilePage(controller.getCurrentUser());
			}
		});
		
		//SPButton.setBounds(519, 11, 126, 26);
		buttons.add(SPButton);
		
		new_SPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 User user = controller.getCurrentUser();
				 //ArrayList<Deck> publicDecks = decklist.getAllPublicDecks();
				 //ArrayList<Deck> userDecks = decklist.getAllCurrentUserDecks();
				 ArrayList<Deck> allDecks;
			        // Combine the public and user decks into a single list
			        allDecks = new ArrayList<Deck>();
			        allDecks.addAll(publicDecks);
			        //allDecks.addAll(userDecks);
			        
				controller.createStudyPlanPage(user, allDecks);
			}
		});
		//new_SPButton.setBounds(340, 11, 169, 26);
		buttons.add(new_SPButton);
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		buttons.add(logout);
		
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

		
	
}
