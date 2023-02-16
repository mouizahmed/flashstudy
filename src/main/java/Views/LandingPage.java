package Views;

import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LandingPage extends JPanel implements ActionListener {

	String username;
	JButton add = new JButton("New Deck");
	JButton browse = new JButton("Home");
	JButton profile = new JButton("Profile");
	JPanel buttons = new JPanel();
	JPanel title = new JPanel();
	
	JLabel titleMsg;
	
	JPanel main;
	CardLayout card;
	
	public LandingPage(JPanel main, CardLayout card, String username) {
		this.main = main;
		this.card = card;
		this.username = username;
		
		this.add(title);
		this.add(buttons);
		initialize();
	}
	
	private void initialize() {
		titleMsg = new JLabel("Welcome " + username + "!");
		title.add(titleMsg);
		
		buttons.add(browse);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttons.add(add);
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttons.add(profile);
		
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

		
	
}
