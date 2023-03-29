package Views;

import javax.swing.JPanel;

import Controller.Controller;
import Models.QuizSession;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuizResults extends JPanel {

	private QuizSession quizSession;
	private Controller controller;
	/**
	 * Create the panel.
	 */
	
	
	
	public QuizResults(QuizSession quizSession, Controller controller) {
		this.quizSession = quizSession;
		this.controller = controller;
		initialize();
	}
	
	
	private void initialize() {
		
		setPreferredSize(new Dimension(750, 500));
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 738, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deckPage(quizSession.getDeck());
			}
		});
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		JLabel title = new JLabel(this.quizSession.getUser().getUsername() + " Results");
		title.setBounds(330, 97, 218, 35);
		add(title);
		
		JLabel score = new JLabel("Score: " + Integer.toString(quizSession.getScore() ) + "/" +   Integer.toString(quizSession.getNumQuestions()));
		score.setBounds(103, 168, 95, 16);
		add(score);
		
		String average = "Average: " + Double.toString(quizSession.getAvgScore()) + "%";
		JLabel avgScore = new JLabel(average);
		avgScore.setBounds(103, 221, 257, 16);
		add(avgScore);
		
		JButton leaderboard = new JButton("Leaderboard");
		leaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.leaderboardPage(quizSession.getDeck());
			}
		});
		leaderboard.setForeground(new Color(255, 255, 255));
		leaderboard.setBackground(new Color(0, 0, 0));
		leaderboard.setBounds(302, 362, 121, 23);
		add(leaderboard);
	}
}
