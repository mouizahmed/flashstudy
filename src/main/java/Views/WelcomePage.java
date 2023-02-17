package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

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
	private Controller controller;
	
	public WelcomePage(Controller controller) {
//		this.main = main;
//		this.card = card;
		this.controller = controller;
		
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
			controller.loginPage();
		} else if (e.getSource() == register) {
			controller.registerPage();
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
