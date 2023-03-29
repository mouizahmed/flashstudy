package Views;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import Models.Question;
import Models.QuizSession;
import Models.TrueFalseQuestion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrueFalseView extends JPanel {

	
	private int index;
	private TrueFalseQuestion question;
	private QuizSession quizSession;
	
	/**
	 * Create the panel.
	 */
	public TrueFalseView(int index, Question question, QuizSession quizSession) {
		this.index = index;
		this.question = (TrueFalseQuestion) question;
		this.quizSession = quizSession;
		initialize();
		
	}
	
	private void initialize() {
		setLayout(null);
		
		JRadioButton trueButton = new JRadioButton("True");
		trueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizSession.answerQuestion(index, "True");
			}
		});
		trueButton.setBounds(94, 176, 121, 24);
		add(trueButton);
		
		JRadioButton falseButton = new JRadioButton("False");
		falseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizSession.answerQuestion(index, "False");
			}
		});
		falseButton.setBounds(242, 176, 121, 24);
		add(falseButton);
		
		JLabel lblNewLabel_1 = new JLabel("Is this true or false?");
		lblNewLabel_1.setBounds(47, 43, 327, 31);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Term:" + question.getQuestion());
		lblNewLabel_2.setBounds(47, 73, 292, 31);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Definition:" + ((TrueFalseQuestion)question).getFakeAnswer());
		lblNewLabel_3.setBounds(47, 105, 307, 31);
		add(lblNewLabel_3);
		
		
		ButtonGroup answer = new ButtonGroup();
		answer.add(trueButton);
		answer.add(falseButton);
		
	}
}
