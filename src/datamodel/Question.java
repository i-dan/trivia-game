package datamodel;

import java.util.ArrayList;
import java.util.Collections;


/*
 * manages a Question object which contain a question, an answer and other three wring answers 
 */
public class Question 
{
	private final String question;
	private final String correctAnswer;
	private ArrayList<String> answers; // should be treated as final after constructor finish its job

	
	/*
	 * Constructor
	 * initialize the relevant instance variables and put the arguments into 
	 */
	public Question(String question, String answer, ArrayList<String> wrongAnswers)
	{
		answers = new ArrayList<String>();
		
		this.question = question;
		correctAnswer = answer;
		answers.add(correctAnswer); // answers contains the same instance as correcrAnswer 
		answers.addAll(wrongAnswers);
		
		Collections.shuffle(answers);
	}

	
	// Getters and setters
	public String getQuestion() {
		return question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	public int getNumOfAnswers() {
		return answers.size();
	}
	
	
	/*
	 * return an array with all of the optional answers
	 */
	public String[] getAnswersArray()
	{
		String[] res = new String[answers.size()];
		for(int i = 0; i < answers.size(); i++)
			res[i] = answers.get(i);
		
		return res;
	}
	
	
	/*
	 * shuffle the order of the answers
	 */
	public void shuffle()
	{
		Collections.shuffle(answers);
	}
	
	
	/*
	 * gets a string which should represent an answer, return true if it's the correct answer
	 */
	public boolean isCorrectAnswer(String s)
	{
		return s.equals(correctAnswer);
	}
	
	
	/*
	 * return a nice string representation of the Question
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Qusetion: " + question + "\n");
		sb.append("Answers:\n");
		for(int i = 0; i < answers.size(); i++)
			sb.append((i+1) + answers.get(i) + "\n"); // +1 since index start from 0
		sb.append("\nCorrect answer: " + answers);
		
		return sb.toString();
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	

}
