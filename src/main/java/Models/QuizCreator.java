package Models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class QuizCreator {
	
	private int questionsNum;
	private String quizID;
	private String deckID;
	private ArrayList<Question> questions = new ArrayList<>();
	
	private String[] questionType = {"multiple-choice", "True or False", "Fill in the blank"};
	
	
	public QuizCreator(Deck deck) {
		this.questionsNum = deck.getAllFlashcards().size();
		this.quizID = UUID.randomUUID().toString();
		this.deckID = deck.getDeckID();
		ArrayList<Flashcard> flashcards = deck.getAllFlashcards();
		
		for (int i=0; i<this.questionsNum; i++) {
			int randomType = new Random().nextInt(questionType.length);
			Question question = null;
			
			if (randomType == 0) {
				String[] options = new String[4];
				options[0] = flashcards.get(i).getAnswer();
				for (int j = 1; j < options.length && j < this.questionsNum; j++) {
					String randomAnswer = flashcards.get(new Random().nextInt(flashcards.size())).getAnswer();
					options[j] = randomAnswer;
				}
				question = new MultipleChoiceQuestion(flashcards.get(i).getQuestion(), flashcards.get(i).getAnswer(), options);
			} else if (randomType == 1) {
				String randomAnswer = flashcards.get(new Random().nextInt(flashcards.size())).getAnswer();
				
				boolean questionAnswer = (randomAnswer.equals(flashcards.get(i).getAnswer()));
				
				question = new TrueFalseQuestion(flashcards.get(i).getQuestion(), questionAnswer, randomAnswer);
			} else if (randomType == 2) {
				String answer = flashcards.get(i).getAnswer();
				String[] words = answer.split(" ");
				int wordLength = words.length;
				int randomWordIndex = new Random().nextInt(wordLength);
				String placeholder = "";
				for (int n = 0; n < words[randomWordIndex].length(); n++) {
					placeholder += "-";
				}
				
				String prompt = answer.replace(words[randomWordIndex], placeholder);
				String missingBlank = words[randomWordIndex];
				question = new FillInTheBlankQuestion(prompt, missingBlank);
			}
			
			
			
			
			questions.add(question);
			
		}
		
		
	}
	
	public ArrayList<Question>getAllQuestions() {
		return questions;
	}
	
	public String getQuizID() {
		return this.quizID;
	}
	
	public String getDeckID() {
		return this.deckID;
	}
	
}
