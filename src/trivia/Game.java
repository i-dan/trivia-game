package trivia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import datamodel.Question;
import datamodel.QuestionsRepository;


/*
 * Manages a nice trivia game. 
 */
public class Game 
{
	private QuestionsRepository questionsRepository;
	private Question currentQuestion;
	private String[] questionsArray;
	int cnt; // correct answers counter
	boolean answerTested; // only one check of question correctness will influence cnt 
	private final int NUM_OF_ANWERS = 4;
	
	
	/*
	 * Constructor:
	 * build the game content.
	 * gets a path to a text-file made according the rules (see below) and process it to build the game content
	 * 
	 * 
	 * about the rules: we have asked to build it by the following rules:
	 * every 5 lines represents a whole question; 
	 * first line: question. second: correct answer. 3 others: wrong answers.
	 * 
	 * we have decided to build the class in expandable manner, so if you want to change the policy of the file's 
	 * format all you have to do is to change the constructor. moreover, you can use a format with variable number of questions
	 * and it will support. (along with the controller class as well) 
	 * Hopefully you we like it :)
	 */
	public Game(String filename)
	{
		questionsRepository = new QuestionsRepository();
		cnt = 0;

		try
		{
			Scanner scan = new Scanner(new File(filename));
		
			while(scan.hasNext())
			{
				// assumption is that the file is properly organized, thus we can safely read a whole question at once 
				String q = scan.nextLine();
				String c = scan.nextLine();
				ArrayList<String> answers = new ArrayList<String>();
				for(int i = 0; i < NUM_OF_ANWERS-1; i++) // -1 for the correct answer already read
					answers.add(scan.nextLine());
				
				Question question = new Question(q, c, answers);
				questionsRepository.addQustion(question);
			}
			scan.close();
		}
		
		catch(NoSuchElementException e) // no line was found
		{
			System.out.println("File's lines number must divided by 5");
			// return false;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening file");
			// return false;
		}
		// return true;
	}
	
	
	/*
	 * return true if there are more questions
	 */
	public boolean hasNext()
	{
		return questionsRepository.hasNext();
	}
	
	
	/*
	 * return a Question object of the next question
	 */
	public Question next()
	{
		currentQuestion = questionsRepository.next();
		questionsArray = currentQuestion.getAnswersArray();
		answerTested = false;

		return  currentQuestion;
	}
	
	
	/*
	 * return a String of the current question
	 */
	public String getCurrentQuestion()
	{
		return currentQuestion.getQuestion();
	}
	
	
	/*
	 * return an array (copy) with all of the optional answers
	 */
	public String[] getAnswersArray()
	{
		return questionsArray.clone();
	}
	
	
	/*
	 * check if the given index for the array from getAnswersArray is the index of the right answer
	 * if so, return true and increment the counter of correct answer for the current game
	 */
	public boolean isCorrectAnswer(int index)
	{
		answerTested = true;
		boolean b = questionsArray[index].equals(currentQuestion.getCorrectAnswer());
		if(b)
			cnt++;
		return b;
	}
	
	
	/*
	 * return the score (0-100) for the current game
	 */
	public int getScore()
	{
		double rel = ((double)cnt)/questionsRepository.getSize();
		return (int) (rel*100);
	}
	
	
	/*
	 * reset the game parameters
	 */
	public void reset()
	{
		cnt = 0;
		questionsRepository.resetIteration();
		questionsRepository.shuffle();
	}
	
	
	
	
	
	
}
