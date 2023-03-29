package Views;

import java.awt.Dimension;

import javax.swing.JPanel;

import Models.Deck;
import Models.Question;
import Models.QuizCreator;
import Models.QuizSession;
import Models.User;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import Controller.Controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class QuizPage extends JPanel {

	private QuizSession quizSession;
	private ArrayList<Question> questions;
	private Controller controller;
	private JPanel questionPanel;
	private JButton nextQuestion;
	private int index = 0;
	
	public QuizPage(QuizSession quizSession, Controller controller) {
		setBackground(new Color(255, 255, 255));
		this.quizSession = quizSession;
		this.controller = controller;
		this.questions = quizSession.getAllQuestions();
		//System.out.println("QUESTIONS QUIZ " + this.questions.get(0).getType());
		this.questionPanel = new JPanel();
		questionPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		questionPanel.setBounds(67, 111, 600, 300);
		add(questionPanel);
		initialize();
	}
	
	private void initialize() {
		setPreferredSize(new Dimension(750, 500));
		setLayout(null);
		nextQuestion = new JButton("Next");
		nextQuestion.setForeground(new Color(255, 255, 255));
		nextQuestion.setBackground(new Color(0, 0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setBounds(0, 0, 738, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deckPage(quizSession.getDeck());
			}
		});
		btnNewButton.setBounds(12, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setForeground(new Color(255, 255, 255));
		profileButton.setBackground(new Color(0, 0, 0));
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		// question
		getQuestion(index);
		
		
		
		
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setForeground(new Color(255, 255, 255));
		btnPrevious.setBackground(new Color(0, 0, 0));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrevious.setBounds(268, 422, 98, 26);
		add(btnPrevious);
		
		checkLast();
		nextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (index+1 >= questions.size()) {
					nextQuestion.setEnabled(false);
					
					JButton submit = new JButton("Submit");
					
					submit.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							controller.quizResults(quizSession);
							
						}
						
					});
					
					remove(nextQuestion);
					submit.setBounds(376, 422, 98, 26);
					add(submit);
					
					revalidate();
					repaint();
				} else {
					System.out.println(quizSession.getScore());
					index += 1;
					getQuestion(index);
				}
			}
		});
		nextQuestion.setBounds(376, 422, 98, 26);
		add(nextQuestion);		
		
		
		
	}
	
	private void getQuestion(int index) {
		// question
				//JPanel questionPanel = null;
				//questionPanel.removeAll();
				this.remove(questionPanel);
				Question question = questions.get(index);
				if (question.getType().equals("Multiple Choice")) {
					questionPanel = new MultipleChoiceView(index, question, quizSession);
				} else if(question.getType().equals("True or False")) {
					questionPanel = new TrueFalseView(index, question, quizSession);
				} else if(question.getType().equals("Fill in the Blank")) {
					questionPanel = new FillInTheBlankView(index, question, quizSession);
				}
				
				//JPanel questionPanel = new JPanel();
				questionPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				questionPanel.setBounds(67, 111, 600, 300);
				add(questionPanel);
				checkLast();
				revalidate();
				repaint();
				

	}
	
	
	private boolean checkLast() {
		
		//return index+1 >= questions.size();
		
		
		if (index+1 >= questions.size()) {
			JButton submit = new JButton("Submit");
			
			submit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					controller.quizResults(quizSession);
					
				}
				
			});
			
			remove(nextQuestion);
			submit.setBounds(376, 422, 98, 26);
			add(submit);
			return true;
		} else {
			return false;
		}
		
		
	}
}
