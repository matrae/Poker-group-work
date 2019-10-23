package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import game.PokerGame;
import model.PokerGameModel;

public class PokerGameView {
	private HBox players;
	private ControlArea controls;
	private PokerGameModel model;
	public Button startGame = new Button("Start Game");
	public Scene scene1, scene2;
	public Stage stage;
	public BorderPane layout2 = new BorderPane();

	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage = stage;

		Label labelMenu = new Label("Choose how many people you would like to play with: ");
		Button twoPlayers = new Button("2");
		Button threePlayers = new Button("3");
		Button fourPlayers = new Button("4");


		// this.setId("startGame"); // Unique ID in the CSS: ToDo

		// create layout 1 and put it into the BorderPane
		BorderPane rootLayout1 = new BorderPane();
		VBox layout1 = new VBox(20);
		rootLayout1.setCenter(layout1);

		// add buttons into VBox for layout1
		layout1.getChildren().addAll(labelMenu, twoPlayers, threePlayers, fourPlayers, startGame);

		// create scene1 with layout1
		scene1 = new Scene(rootLayout1, 600, 500);

		//needs to go into controller: toDo
		Button goBack = new Button("Back to Menu");
		goBack.setOnAction(e -> stage.setScene(scene1));

		// Create layout 2
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

		// Put players, controls & goBack Button into a BorderPane
		layout2.setCenter(players);
		layout2.setBottom(controls);
		layout2.setLeft(goBack);

		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

		// Create the second scene using our layout; then display it
		Scene scene2 = new Scene(layout2);

		scene2.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
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
