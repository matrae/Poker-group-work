package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import game.PokerGame;
import model.PokerGameModel;

public class PokerGameView {
	private HBox players;
	private ControlArea controls;
	
	private PokerGameModel model;
	Scene scene1;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		
		Label labelMenu = new Label("This is the menu");
		Button twoPlayers = new Button("2");
		Button threePlayers = new Button("3");
		Button fourPlayers = new Button("4");
		Button startGame = new Button("Start Game!");
		
		
		
		BorderPane rootLayout1 = new BorderPane();
		VBox layout1 = new VBox(20);
		rootLayout1.setCenter(layout1);
		
		Region spacer = new Region();
		spacer.setPrefHeight(30);
		
		//add buttons into VBox for the first menu layout
		layout1.getChildren().addAll(labelMenu, twoPlayers, threePlayers, fourPlayers, spacer, startGame);
		
		//create scene1
		scene1 = new Scene(layout1, 200, 200);
		stage.setScene(scene1);
		
		
		// Create all of the player panes we need, and put them into an HBox
		players = new HBox();
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.getChildren().add(pp);
		}
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setCenter(players);
		root.setBottom(controls);
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene2 = new Scene(root);
        
        //when startGame is clicked go to second scene (scene2)
      	startGame.setOnAction(e-> stage.setScene(scene2));
       
      	scene2.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        
        stage.setScene(scene2);
        stage.show();		
	}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}
}
