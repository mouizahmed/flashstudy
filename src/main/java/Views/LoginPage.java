package Views;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JPasswordField;
import java.awt.GridLayout;

public class LoginPage extends JPanel implements ItemListener, ActionListener  {
	
	JPanel main;
	CardLayout card;
	Controller controller;
	
	JPanel title = new JPanel();
	JLabel loginMsg = new JLabel("Login");
	
	JPanel buttons = new JPanel();
	JButton back = new JButton("Back");
	JButton submit = new JButton("Submit");
	
	JPanel namePanel = new JPanel();
	JLabel nameLabel = new JLabel("Username");
	JTextField name = new JTextField();
	
	JPanel passwordPanel = new JPanel();
	JLabel passwordLabel = new JLabel("Password");
	
	WelcomePage welcomePage;
	private final JPasswordField password = new JPasswordField();
	
	public LoginPage(Controller controller) {
		this.controller = controller;
		//this.welcomePage = welcomePage;
//		this.main = main;
//		this.card = card;
		initialize();
	}
	
	public void initialize() {
		
		
		title.add(loginMsg);
		this.add(title);
		namePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		namePanel.add(nameLabel);
		name.setPreferredSize(new Dimension(100, 20));
		namePanel.add(name);
		passwordPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		passwordPanel.add(passwordLabel);
		
		this.add(namePanel);
		this.add(passwordPanel);
		password.setColumns(12);
		
		passwordPanel.add(password);
	
		back.addActionListener(this);
		submit.addActionListener(this);
		buttons.add(back);
		buttons.add(submit);
		this.add(buttons);
		
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			//WelcomePage welcomePage = new WelcomePage();
//			this.setVisible(false);
//			welcomePage.setVisible(true);
			controller.welcomePage();
		} else if (e.getSource() == submit) {
			
			controller.login(name.getText(), String.valueOf(password.getPassword()));
			
			//LandingPage landingPage = new LandingPage(main, card, name.getText());
			
			
//			boolean loggedIn = false;
//			
//			loggedIn = MiddleTier.login(name.getText(), password.getText());
//			
//			if (loggedIn == true) {
//				main.add(landingPage, "landing");
//				card.show(main, "landing");
//			}
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
