package flashstudy;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

import Controller.Controller;
import Models.EmailSender;
import Views.BrowsePublicDeckPage;
import Views.CreateDeckPage;
import Views.LandingPage;
import Views.LoginPage;
import Views.RegisterPage;
import Views.WelcomePage;



public class Main {
	
	public static void main (String[] args) {
		
		JFrame main = new JFrame("Flash Study");
		
		
		JPanel container = new JPanel();
		main.add(container);
		CardLayout card = new CardLayout();
		container.setLayout(card);
		// container.setVisible(true);
		
		Controller controller = new Controller(container, card);
		WelcomePage welcomePage = new WelcomePage(controller);
		container.add(welcomePage, "welcomePage");
		
		LoginPage loginPage = new LoginPage(controller);
		container.add(loginPage, "loginPage");
		
		RegisterPage registerPage = new RegisterPage(controller);
		container.add(registerPage, "registerPage");
		
//		LandingPage landingPage = new LandingPage(controller);
//		container.add(landingPage, "landingPage");
		
//		CreateDeckPage createDeckPage = new CreateDeckPage(controller);
//		container.add(createDeckPage, "createDeckPage");
		
//		BrowsePublicDeckPage browsePublicDeckPage = new BrowsePublicDeckPage(controller);
//		container.add(browsePublicDeckPage, "browsePublicDeckPage");
		
		

		card.show(container, "1");
		welcomePage.setVisible(true);
		
		main.setSize(750, 500);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		
		//EmailSender email = new EmailSender();
		//MiddleTier app = new MiddleTier();
		
		
	}
}
