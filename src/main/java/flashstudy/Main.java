package flashstudy;

import Controller.Controller;
import Views.LoginPage;
import Views.RegisterPage;
import Views.WelcomePage;

import javax.swing.*;
import java.awt.*;


public class Main {
	
	private JPanel container;
	private CardLayout card;
	private JFrame main;
	private Controller controller;
	private WelcomePage welcomePage;
	
	public Main() {
		main = new JFrame("Flash Study");
		
		
		container = new JPanel();
		main.add(container);
		card = new CardLayout();
		container.setLayout(card);
		// container.setVisible(true);
		
		controller = new Controller(container, card);
		welcomePage = new WelcomePage(controller);
		container.add(welcomePage, "welcomePage");
		
		LoginPage loginPage = new LoginPage(controller);
		container.add(loginPage, "loginPage");
		RegisterPage registerPage = new RegisterPage(controller);
		container.add(registerPage, "registerPage");
		
		card.show(container, "1");
		welcomePage.setVisible(true);
		
		main.setSize(750, 500);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
	
	}
	
	public static void main (String[] args) {
		Main main = new Main();
		

	}
	
	public WelcomePage getWelcomePage() {
		return this.welcomePage;
	}
	
	public JFrame getFrame() {
		return this.main;
	}
	
	public CardLayout getCard() {
		return this.card;
	}
	
	public JPanel getContainer() {
		return this.container;
	}
	
	public Controller getController() {
		return this.controller;
	}
}
