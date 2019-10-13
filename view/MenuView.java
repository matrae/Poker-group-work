package view;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//View where the menu & buttons are displayed
public class MenuView {
	
	Button twoPlayers = new Button();
	Button threePlayers = new Button();
	Button fourPlayers = new Button();
	Button startGame = new Button();
		
	public static void main(String[] args) {
		launch();
	}
	
	
	public void start(Stage primaryStage) {
		HBox root = new HBox();
		Region spacer = new Region();
		spacer.setPrefHeight(30);
		VBox boxCenter = new VBox(twoPlayers, threePlayers, fourPlayers, startGame);
	 
	 		
		boxCenter.getChildren().addAll(txtField, boxBottom);
		
		
		
		root.getChildren().addAll(boxCenter, root);
		
		Scene scene = new Scene(root);
	    primaryStage.setTitle("PokerGame Menu");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}


	}

