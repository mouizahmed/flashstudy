package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Models.FillInTheBlankQuestion;
import Models.Question;
import Models.QuizSession;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FillInTheBlankView extends JPanel {
	private JTextField answer;
	private FillInTheBlankQuestion question;
	private int index;
	private QuizSession quizSession;
	/**
	 * Create the panel.
	 */
	public FillInTheBlankView(int index, Question question, QuizSession quizSession) {
		
		this.question = (FillInTheBlankQuestion) question;
		this.index = index;
		this.quizSession = quizSession;
		initialize();
	}
	
	private void initialize() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill in the blank");
		lblNewLabel.setBounds(190, 5, 135, 14);
		add(lblNewLabel);
		
		JLabel prompt = new JLabel(question.getQuestion());
		prompt.setBounds(36, 95, 342, 41);
		add(prompt);
		
		answer = new JTextField();
		answer.setBounds(162, 149, 114, 20);
		add(answer);
		answer.setColumns(10);
		
		JButton saveAnswer = new JButton("Save Answer");
		saveAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizSession.answerQuestion(index, answer.getText());
			}
		});
		saveAnswer.setBounds(148, 182, 128, 26);
		add(saveAnswer);
	}
	
}
