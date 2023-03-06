package Models;

import java.util.ArrayList;
import java.util.Random;

public class QuizCreator {
	
	private int questionsNum;
	private int correct;
	private ArrayList<Question> questions = new ArrayList<>();
	
	private String[] questionType = {"multiple-choice", "True or False", "Fill in the blank"};
	
	
	public QuizCreator(Deck deck, User user) {
		this.questionsNum = deck.getAllFlashcards().size();
		
		ArrayList<Flashcard> flashcards = deck.getAllFlashcards();
		
		for (int i=0; i<this.questionsNum; i++) {
			int randomType = new Random().nextInt(questionType.length);
			Question question = null;
			
			if (randomType == 0) {
				String[] options = new String[4];
				options[0] = flashcards.get(i).getAnswer();
				for (int j = 1; j < options.length; j++) {
					String randomAnswer = flashcards.get(new Random().nextInt(flashcards.size())).getAnswer();
					options[j] = randomAnswer;
				}
				question = new MultipleChoiceQuestion(flashcards.get(i).getQuestion(), flashcards.get(i).getAnswer(), options);
			} else if (randomType == 1) {
				String randomAnswer = flashcards.get(new Random().nextInt(flashcards.size())).getAnswer();
				boolean questionAnswer = (randomAnswer.equals(flashcards.get(i).getAnswer()));
				question = new TrueFalseQuestion(flashcards.get(i).getQuestion(), questionAnswer);
			} else if (randomType == 2) {
				
			}
			
			
			
			questions.add(question);
			
		}
	}
	
	public ArrayList<Question>getAllQuestions() {
		return questions;
	}
	
	
}
