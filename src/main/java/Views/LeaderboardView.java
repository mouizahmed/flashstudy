package Views;

import javax.swing.JPanel;

import Models.Leaderboard;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;

public class LeaderboardView extends JPanel {

	private Leaderboard leaderboard;
	/**
	 * Create the panel.
	 */
	public LeaderboardView(Leaderboard leaderboard) {		
		this.leaderboard = leaderboard;
		initialize();
	}

	
	private void initialize() {
		setPreferredSize(new Dimension(750, 500));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 738, 47);
		panel.setLayout(null);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
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

	}
}
