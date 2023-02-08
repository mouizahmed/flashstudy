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

import Controller.MiddleTier;

public class RegisterPage extends JPanel implements ItemListener, ActionListener {
	
	JPanel main;
	CardLayout card;
	
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
	JTextField password = new JTextField();
	
	JPanel confirmPasswordPanel = new JPanel();
	JLabel confirmPasswordLabel = new JLabel("Confirm Password");
	JTextField confirmPassword = new JTextField();
	
	WelcomePage welcomePage;
	
	public RegisterPage(WelcomePage welcomePage, JPanel main, CardLayout card) {
		
		this.welcomePage = welcomePage;
		this.main = main;
		this.card = card;
		initialize();
		
	}
	
	public void initialize() {
	
		title.add(RegisterMsg);
		this.add(title);
		
		
		namePanel.add(nameLabel);
		name.setPreferredSize(new Dimension(100, 20));
		namePanel.add(name);
		
		emailPanel.add(emailLabel);
		email.setPreferredSize(new Dimension(100, 20));
		emailPanel.add(email);
		
		passwordPanel.add(passwordLabel);
		password.setPreferredSize(new Dimension(100, 20));
		passwordPanel.add(password);
		
		confirmPasswordPanel.add(confirmPasswordLabel);
		confirmPassword.setPreferredSize(new Dimension(100, 20));
		confirmPasswordPanel.add(confirmPassword);
		
		this.add(namePanel);
		this.add(emailPanel);
		this.add(passwordPanel);
		this.add(confirmPasswordPanel);
		
		
		
		
		
		
		back.addActionListener(this);
		submit.addActionListener(this);
		buttons.add(back);
		buttons.add(submit);
		this.add(buttons);
		
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		name.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		confirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		confirmPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			//WelcomePage welcomePage = new WelcomePage();
			this.setVisible(false);
			welcomePage.setVisible(true);
		} else if (e.getSource() == submit) {
			LandingPage landingPage = new LandingPage(main, card, name.getText());
			
			
		boolean registered = false;
		try {
			registered = MiddleTier.createNewUser(name.getText(), email.getText(), password.getText());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			if (registered == true) {
				main.add(landingPage, "landing");
				card.show(main, "landing");
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
