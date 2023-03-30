package Views;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JPanel implements ActionListener {
	
	private Controller controller;
	JButton add = new JButton("New Deck");
	JButton browse = new JButton("Browse");
	JButton logout = new JButton("Logout");
	JButton emailRemiender = new JButton("Reminder");

	JPanel buttons = new JPanel();
	JPanel title = new JPanel();
	
	JLabel titleMsg;
	
	JPanel main;
	CardLayout card;
	
	public LandingPage(Controller controller) {
		setBackground(new Color(255, 255, 255));

		this.controller = controller;
		title.setBackground(new Color(255, 255, 255));
//		this.main = main;
//		this.card = card;
//		this.username = username;
		
		this.add(title);
		buttons.setBackground(new Color(255, 255, 255));
		this.add(buttons);
		initialize();
	}
	
	private void initialize() {
		titleMsg = new JLabel("Welcome " + controller.getCurrentUser().getUsername() + "!");
		title.add(titleMsg);
		browse.setBackground(new Color(0, 0, 0));
		browse.setForeground(new Color(255, 255, 255));
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.browse();
			}
		});
		
		buttons.add(browse);
		add.setBackground(new Color(0, 0, 0));
		add.setForeground(new Color(255, 255, 255));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createDeckPage();
			}
		});
		buttons.add(add);
		logout.setBackground(new Color(0, 0, 0));
		logout.setForeground(new Color(255, 255, 255));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		buttons.add(logout);
		emailRemiender.setBackground(new Color(0, 0, 0));
		emailRemiender.setForeground(new Color(255, 255, 255));
		emailRemiender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.emailPage();
			}
		});
		buttons.add(emailRemiender);

		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

		
	
}
