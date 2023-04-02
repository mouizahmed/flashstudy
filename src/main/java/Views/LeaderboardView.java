package Views;

import javax.swing.JPanel;

import Controller.Controller;
import Models.Leaderboard;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LeaderboardView extends JPanel {

	private Leaderboard leaderboard;
	private Controller controller;
	/**
	 * Create the panel.
	 */
	public LeaderboardView(Leaderboard leaderboard, Controller controller) {
		setBackground(new Color(255, 255, 255));		
		this.leaderboard = leaderboard;
		this.controller = controller;
		initialize();
	}

	
	private void initialize() {
		setPreferredSize(new Dimension(750, 500));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 738, 47);
		panel.setLayout(null);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.previous();
			}
		});
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.profilePage(controller.getCurrentUser());
			}
		});
		profileButton.setBackground(new Color(0, 0, 0));
		profileButton.setForeground(new Color(255, 255, 255));
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		JLabel title = new JLabel(this.leaderboard.getDeck().getDeckTitle());
		title.setBounds(314, 97, 313, 16);
		add(title);
		
		JLabel title2 = new JLabel("Leaderboard");
		title2.setBounds(306, 125, 86, 16);
		add(title2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(180, 172, 364, 255);
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		for (int i = 0; i < leaderboard.getPlayers().size() && i < 5; i++) {
			JLabel player = new JLabel(Integer.toString(i+1) + ". " + leaderboard.getPlayers().get(i).getUsername() + " " + Double.toString(leaderboard.getPlayers().get(i).getScore()));
			
			panel_1.add(player);
		}
		
		if (panel_1.getComponentCount() == 0) {
			JLabel noPlayers = new JLabel("No Players");
			panel_1.add(noPlayers);
		}

	}
}
