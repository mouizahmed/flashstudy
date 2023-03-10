package Views;

import javax.swing.JPanel;

import Models.QuizSession;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;

public class QuizResults extends JPanel {

	private QuizSession quizSession;
	/**
	 * Create the panel.
	 */
	public QuizResults(QuizSession quizSession) {
		setPreferredSize(new Dimension(750, 500));
		this.quizSession = quizSession;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 738, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
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
		initialize();
	}
	
	
	private void initialize() {
		
	}
}
