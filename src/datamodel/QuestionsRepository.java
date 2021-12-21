package datamodel;

import java.util.ArrayList;
import java.util.Collections;


/*
 * manages a repository for question and allow to retrieve them one by one 
 */
public class QuestionsRepository 
{
	private ArrayList<Question> repository;
	private int cur;
	
	
	/*
	 * Constructor
	 */
	public QuestionsRepository()
	{
		repository = new ArrayList<Question>();
		cur = 0;
	}
	
	
	/* getters and setters - irrelevant */
	
	
	/*
	 * return the number of current being stored 
	 */
	public int getSize()
	{
		return repository.size();
	}
	

	/*
	 * add a Question to the repository
	 */
	public void addQustion(Question q)
	{
		if(q != null)
			repository.add(q);
	}
	
	/*
	public boolean addFile(String filename)
	{

	}
	*/
	
	
	/*
	 * shuffle the order in the repository and the order of the answers at any Question object
	 */
	public void shuffle()
	{
		/* if cur != 0 throw error */
		for(Question q : repository)
			q.shuffle();
		Collections.shuffle(repository);
	}
	
	
	/*
	 * swipe the repository
	 */
	public void clear()
	{
		repository.clear();
	}
	
	
	/*
	 * return true if the repository is empty
	 */
	public boolean isEmpty()
	{
		return repository.isEmpty();
	}
	
	
	/*
	 * return true if ther's a Question to retrieve
	 */
	public boolean hasNext()
	{
		return cur < repository.size();
	}
	
	
	/*
	 * return the next Question
	 */
	public Question next()
	{
		// might throw an exception if you have not used hasNext before 
		return repository.get(cur++);
	}
	
	
	/*
	 * return the iteration cursor to the first element
	 */
	public void resetIteration()
	{
		cur = 0;
	}
	
	
	
	
	
	
	
	
	

}
