package Views;

import javax.swing.JPanel;

import Models.MultipleChoiceQuestion;
import Models.Question;
import Models.QuizSession;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MultipleChoiceView extends JPanel {

	private MultipleChoiceQuestion question;
	private String[] options;
	private int index;
	private String userAnswer;
	private QuizSession quizSession;
	
	/**
	 * Create the panel.
	 */
	public MultipleChoiceView(int index, Question question, QuizSession quizSession) {
		this.question = (MultipleChoiceQuestion) question;
		this.index = index;
		this.quizSession = quizSession;
		this.options = ((MultipleChoiceQuestion) question).getAllOptions();
		
		
		
		initialize();
		
	}
	
	
	private void initialize() {
		setLayout(null);
		
		JLabel questionLabel = new JLabel(this.question.getQuestion());
		questionLabel.setBounds(36, 59, 354, 47);
		add(questionLabel);
		
		JRadioButton answer1 = new JRadioButton(this.options[0]);
		answer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizSession.answerQuestion(index, options[0]);
			}
		});
		answer1.setBounds(79, 133, 109, 23);
		add(answer1);
		
		JRadioButton answer2 = new JRadioButton(this.options[1]);
		answer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizSession.answerQuestion(index, options[1]);
			}
		});
		answer2.setBounds(220, 133, 109, 23);
		add(answer2);
		
		JRadioButton answer3 = new JRadioButton(this.options[2]);
		answer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizSession.answerQuestion(index, options[2]);
			}
		});
		answer3.setBounds(79, 185, 109, 23);
		add(answer3);
		
		JRadioButton answer4 = new JRadioButton(this.options[3]);
		answer4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizSession.answerQuestion(index, options[3]);
			}
		});
		answer4.setBounds(220, 185, 109, 23);
		add(answer4);
		
		ButtonGroup answer = new ButtonGroup();
		answer.add(answer1);
		answer.add(answer2);
		answer.add(answer3);
		answer.add(answer4);

	}
	
	
	public void setAnswer(String answer) {
		this.quizSession.answerQuestion(index, answer);
	}
}
