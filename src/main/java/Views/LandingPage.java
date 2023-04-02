package Views;

import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.Controller;
import Models.Deck;
import Models.StudyP

public class LandingPage extends JPanel implements ActionListener {
	private ArrayList<Deck> publicDecks = new ArrayList<>();
	//private ArrayList<Deck> publicDecks = new ArrayList<>();
	private Controller controller;
  public StudyPlan studyPlan;
	
	String username;
	JButton add = new JButton("New Deck");
	JButton browse = new JButton("Browse");
	JButton logout = new JButton("Logout");
  	JButton emailRemiender = new JButton("Reminder");
	JButton SPButton = new JButton("Your Study Plan");
	JButton new_SPButton = new JButton("Create New Study Plan");

	JPanel buttons = new JPanel();
	JPanel title = new JPanel();
	
	JLabel titleMsg;
	
	JPanel main;
	CardLayout card;
	private final JButton btnNewButton = new JButton("Profile");
	
	public LandingPage(Controller controller) {
		setBackground(new Color(255, 255, 255));

		this.controller = controller;
		title.setBackground(new Color(255, 255, 255));
//		this.main = main;
//		this.card = card;
//		this.username = username;
		
		this.add(title);
		buttons.setBackground(new Color(255, 255, 255));
		this.add(buttons);
		initialize();
	}
	
	private void initialize() {
		titleMsg = new JLabel("Welcome " + controller.getCurrentUser().getUsername() + "!");
		title.add(titleMsg);
		browse.setBackground(new Color(0, 0, 0));
		browse.setForeground(new Color(255, 255, 255));
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.browse();
			}
		});
		
		buttons.add(browse);
		add.setBackground(new Color(0, 0, 0));
		add.setForeground(new Color(255, 255, 255));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createDeckPage();
			}
		});
		buttons.add(add);
    
    SPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = controller.getCurrentUser();
				controller.UserStudyPlannerPage(user, studyPlan);
			}
		});
		
		//SPButton.setBounds(519, 11, 126, 26);
		buttons.add(SPButton);
		
		new_SPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 User user = controller.getCurrentUser();
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

    logout.setBackground(new Color(0, 0, 0));
		logout.setForeground(new Color(255, 255, 255));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.profilePage(controller.getCurrentUser());
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		
		buttons.add(btnNewButton);
		buttons.add(logout);
		emailRemiender.setBackground(new Color(0, 0, 0));
		emailRemiender.setForeground(new Color(255, 255, 255));
		emailRemiender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.emailPage();
			}
		});
		buttons.add(emailRemiender);

		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

		
	
}
