package Views;

import Controller.Controller;
import Models.EmailMessageMaker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RegisterPage extends JPanel implements ItemListener, ActionListener {
	
	JPanel main;
	CardLayout card;
	private Controller controller;
	
	JPanel title = new JPanel();
	JLabel RegisterMsg = new JLabel("Register");
	
	JPanel buttons = new JPanel();
	JButton back = new JButton("Back");
	JButton submit = new JButton("Submit");
	
	JPanel namePanel = new JPanel();
	JLabel nameLabel = new JLabel("Username");
	JTextField name = new JTextField();
	
	JPanel emailPanel = new JPanel();
	JLabel emailLabel = new JLabel("Email");
	JTextField email = new JTextField();
	
	JPanel passwordPanel = new JPanel();
	JLabel passwordLabel = new JLabel("Password");
	
	JPanel confirmPasswordPanel = new JPanel();
	JLabel confirmPasswordLabel = new JLabel("Confirm Password");
	
	WelcomePage welcomePage;
	private final JPasswordField password = new JPasswordField();
	private final JPasswordField confirmPassword = new JPasswordField();
	
	public RegisterPage(Controller controller) {
		setBackground(new Color(255, 255, 255));
		
		this.controller = controller;
//		this.welcomePage = welcomePage;
//		this.main = main;
//		this.card = card;
		initialize();
		
	}
	
	public void initialize() {
		title.setBackground(new Color(255, 255, 255));
	
		title.add(RegisterMsg);
		this.add(title);
		namePanel.setBackground(new Color(255, 255, 255));
		namePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		namePanel.add(nameLabel);
		name.setPreferredSize(new Dimension(100, 20));
		namePanel.add(name);
		emailPanel.setBackground(new Color(255, 255, 255));
		emailPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		emailPanel.add(emailLabel);
		email.setPreferredSize(new Dimension(100, 20));
		emailPanel.add(email);
		passwordPanel.setBackground(new Color(255, 255, 255));
		passwordPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		passwordPanel.add(passwordLabel);
		confirmPasswordPanel.setBackground(new Color(255, 255, 255));
		confirmPasswordPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		confirmPasswordPanel.add(confirmPasswordLabel);
		
		this.add(namePanel);
		this.add(emailPanel);
		this.add(passwordPanel);
		password.setPreferredSize(new Dimension(100, 20));
		passwordPanel.add(password);
		this.add(confirmPasswordPanel);
		back.setForeground(new Color(255, 255, 255));
		back.setBackground(new Color(0, 0, 0));
		
		
		
		
		
		
		back.addActionListener(this);
		submit.setBackground(new Color(0, 0, 0));
		submit.setForeground(new Color(255, 255, 255));
		submit.addActionListener(this);
		buttons.setBackground(new Color(255, 255, 255));
		buttons.setLayout(new GridLayout(0, 2, 0, 0));
		buttons.add(back);
		buttons.add(submit);
		this.add(buttons);
		
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		name.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		confirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		confirmPassword.setPreferredSize(new Dimension(100, 20));
		
		confirmPasswordPanel.add(confirmPassword);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			controller.welcomePage();
		} else if (e.getSource() == submit) {
			System.out.println(String.valueOf(password.getPassword()));
			controller.createNewUser(name.getText(), email.getText(), String.valueOf(password.getPassword()), String.valueOf(confirmPassword.getPassword()));

			//send New Registered User email
			EmailMessageMaker emailMessageMaker = new EmailMessageMaker();
			emailMessageMaker.sendWelcomeEmail(email.getText(),name.getText());

			//LandingPage landingPage = new LandingPage(main, card, name.getText());
			
			
//		boolean registered = false;
//		try {
//			registered = MiddleTier.createNewUser(name.getText(), email.getText(), password.getText(), confirmPassword.getText());
//		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//			
//			if (registered == true) {
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
