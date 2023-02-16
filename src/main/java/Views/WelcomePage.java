package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class WelcomePage extends JPanel implements ItemListener, ActionListener {
	
	JButton login;
	JButton register;
	JPanel buttons = new JPanel();
	JPanel main;
	CardLayout card;
	private JLabel lblWelcomeToFlashstudy;
	
	public WelcomePage(JPanel main, CardLayout card) {
		this.main = main;
		this.card = card;
		
		lblWelcomeToFlashstudy = new JLabel("Welcome to FlashStudy!");
		lblWelcomeToFlashstudy.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblWelcomeToFlashstudy);
		this.add(buttons);
		initialize();
	}
	
	
	private void initialize() {
		
		login = new JButton("Login");
		login.addActionListener(this);
		buttons.add(login);
		
		register = new JButton("Register");
		register.addActionListener(this);
		buttons.add(register);
		
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		
		
//		this.add(welcomeMsg);
//		this.add(login);
//		this.add(register);
		
//		welcomeMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
//		login.setAlignmentX(Component.CENTER_ALIGNMENT);
//		register.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getSource());
		if (e.getSource() == login) {
			System.out.println("LOGIN!");
			//this.setVisible(false);
			LoginPage loginPage = new LoginPage(this, main, card);
			main.add(loginPage, "login");
			card.show(main, "login");
			
			//loginPage.setVisible(true);
		} else {
			System.out.println("REGISTER!");
			//this.setVisible(false);
			RegisterPage registerPage = new RegisterPage(this, main, card);
			main.add(registerPage, "register");
			card.show(main, "register");
		}
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
