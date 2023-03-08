package Views;

import java.awt.Dimension;

import javax.swing.JPanel;

import Models.Deck;
import Models.QuizCreator;
import Models.User;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class QuizPage extends JPanel {

	private QuizCreator quizCreator;
	
	public QuizPage(Deck deck, User user) {
		this.quizCreator = new QuizCreator(deck, user);
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
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(67, 111, 600, 300);
		add(panel_1);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrevious.setBounds(268, 422, 98, 26);
		add(btnPrevious);
		
		JButton nextQuestion = new JButton("Next");
		nextQuestion.setBounds(376, 422, 98, 26);
		add(nextQuestion);
	}
}
