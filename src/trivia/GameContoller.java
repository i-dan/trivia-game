package trivia;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;


/*
 * manage a GUI for a trivia game
 */
public class GameContoller 
{

    @FXML
    private Label question;

    @FXML
    private VBox options;
    
    @FXML
    private Button confirmBtn;

    @FXML
    private Button nextBtn;
    
    @FXML
    private Label prompt;
    
    private ToggleGroup tg;
    
    private RadioButton[] rbs;
    
    private Game game;
    
    private String[] answers;
    
    private boolean first;
        
    
    /*
     * initialize the game interface with the relevant content
     */
    public void initialize() // Always add to the basic template
    {
    	game = new Game("trivia.txt");
    	if(!game.hasNext())
    		throw new IllegalArgumentException("File is empty!");
        nextBtn.setDisable(true);
        first = true;
    	showNextQuestionGUI();
    	first = false;
    }
    
    
    /*
     * carry out the relevant operations when the user press confirm 
     */
    @FXML
    void confirmPressed(ActionEvent event) 
    {
    	// identify the number of radioBtn (zero based, so we subtract 1 from the number of question)
    	String s = ((RadioButton)tg.getSelectedToggle()).getText();
    	int i = Integer.parseInt(s.substring(0, s.indexOf('.'))) - 1; // the form is: "n.) xxxx" so we extract n. and -1 since
    	
    	if(game.isCorrectAnswer(i))
        	prompt.setText("Correct answer!");
    	else
        	prompt.setText("Wrong answer!");
    	prompt.setVisible(true);
    	confirmBtn.setDisable(true);	
        nextBtn.setDisable(false);
        
        
        if(!game.hasNext())
        {
        	nextBtn.setText("Finish");
			for(i = 0; i < rbs.length; i++)
				rbs[i].setDisable(true);
        }
        	
    }

    
    /*
     * carry out the relevant operations when the user press next 
     */
    @FXML
    void nextPressed(ActionEvent event) 
    {
  
    	if(nextBtn.getText().equals("Next")) // game is not yet over
    	{
            nextBtn.setDisable(true);
        	confirmBtn.setDisable(false);
        	showNextQuestionGUI();
    	}
    	

    	else //nextBtn.getText().equals("Finish")
    	{
			int again = JOptionPane.showConfirmDialog(null, "End of the game, your score is: " + game.getScore()
					+ "\nWould you like to play again?");
			if(again == JOptionPane.YES_OPTION)
			{
				game.reset();
				nextBtn.setText("Next");
				nextBtn.setDisable(true);
				confirmBtn.setDisable(false);
				showNextQuestionGUI();
			}
			
			else
				((Stage)options.getScene().getWindow()).close();
    	}
    }
    
    
    /*
     * show the next question on the screen
     */
    private void showNextQuestionGUI()
    {
 		game.next();
 		prompt.setVisible(false);
 		
		String q;
	 	q = game.getCurrentQuestion();
    	answers = game.getAnswersArray(); // throws exception if file is empty
    	question.setText(q);
    	options.getChildren().removeAll(options.getChildren());
    	
    	// determine the size of the Hbox which contains the answers 
    	options.setPrefSize(options.getPrefWidth(), (answers.length)*25);  // 25 = padding + radio text
    	Insets padding = new Insets(0,0,15,0); //15px bottom padding foe every radioButton

    	/* take care later !! */
    	if(first)
    		tg = new ToggleGroup(); // if we want the program to be extendible we have no choice but to clear it
    	rbs = new RadioButton[answers.length];
    	for(int i = 0; i < answers.length; i++)
    	{
    		rbs[i] = new RadioButton((i+1) +".)	" + answers[i]);
    		rbs[i].setPadding(padding);
    		rbs[i].setToggleGroup(tg);
    		options.getChildren().add(rbs[i]);
    	}
    	rbs[0].setSelected(true); // prevent a null pointer exception
    }

}
