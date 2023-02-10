package flashstudy;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

import Controller.MiddleTier;
import Views.WelcomePage;



public class Main {
	
	public static void main (String[] args) {
		
		JFrame main = new JFrame("Flash Study");
		
		
		JPanel container = new JPanel();
		main.add(container);
		CardLayout card = new CardLayout();
		container.setLayout(card);
		// container.setVisible(true);
		
		WelcomePage welcomePage = new WelcomePage(container, card);
		container.add(welcomePage, "1");
		
		
		
		card.show(container, "1");
		welcomePage.setVisible(true);
		
		main.setSize(750, 500);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		
		
		MiddleTier app = new MiddleTier();
		
	}
}
