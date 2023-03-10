package Views;

import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

public class LandingPage extends JPanel implements ActionListener {
	
	private Controller controller;
	String username;
	JButton add = new JButton("New Deck");
	JButton browse = new JButton("Browse");
	JButton logout = new JButton("Logout");
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
