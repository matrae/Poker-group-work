package view;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//all of this needs to be added to the PokerGameView!! --> ToDo Celine
//View where the menu & buttons are displayed
public class MenuView extends Application {
	
	Button twoPlayers = new Button();
	Button threePlayers = new Button();
	Button fourPlayers = new Button();
	Button startGame = new Button();
		
	public static void main(String[] args) {
		launch();
	}
	
	
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		VBox pane2 = new VBox();
		Region spacer = new Region();
		spacer.setPrefHeight(30);
		
		//add buttons into VBox
		pane2.getChildren().addAll(twoPlayers, threePlayers, fourPlayers, spacer, startGame);
		
		//add pane2(VBox) into the borderPane
		pane.setCenter(pane2);
			
		Scene scene = new Scene(pane, 700, 550);
	    primaryStage.setTitle("PokerGame Menu");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	   
	}

}

