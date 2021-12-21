package trivia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/*
 * Start (along with the the controller class) a nice application manages a simple trivia game
 */
public class Main extends Application
{ 
	
	public void start(Stage stage) throws Exception
	{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("Game.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("Trivia"); 
		stage.setScene(scene); 
		stage.setResizable(false);
		stage.show();
	} 
	
	public static void main(String[] args) { 
		launch(args); 
		System.out.println();
	} 
	

}
