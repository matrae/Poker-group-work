package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import game.PokerGame;
import model.PokerGameModel;

public class PokerGameView {
	private HBox players;
	private ControlArea controls;
	private PokerGameModel model;
	
	public Button startGame = new Button("Start Game");
	public Button twoPlayers = new Button("2");
	public Button threePlayers = new Button("3");
	public Button fourPlayers = new Button("4");
	
	public Scene scene1, scene2;
	public Stage stage;
	public BorderPane layout2 = new BorderPane();

	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage = stage;

		Label labelMenu = new Label("Choose player number: ");
				

		// create layout 1 and put it into the BorderPane
		BorderPane rootLayout1 = new BorderPane();
		VBox layout1 = new VBox(10);
		rootLayout1.setId("menuPane"); //CSS
		layout1.setPadding(new Insets(20, 50, 50, 50));
		rootLayout1.setCenter(layout1);
		layout1.setAlignment(Pos.CENTER);

		// add buttons into VBox for layout1
		layout1.getChildren().addAll(labelMenu, twoPlayers, threePlayers, fourPlayers, startGame);
		labelMenu.getStyleClass().add("labelMenu"); //CSS
		startGame.getStyleClass().add("startGame-button"); //CSS
		twoPlayers.getStyleClass().add("twoPlayers-button"); //CSS
		threePlayers.getStyleClass().add("threePlayers-button"); //CSS
		fourPlayers.getStyleClass().add("fourPlayers-button"); //CSS
		

		// create scene1 with layout1
		scene1 = new Scene(rootLayout1, 550, 350);

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

		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

		// Create the second scene using our layout; then display it
		scene2 = new Scene(layout2);

		scene1.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
		scene2.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
		stage.setTitle("Poker Miniproject");

		stage.setScene(scene1);
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
	
	public Button getGoBackButton() {
		return controls.goBack;
	}

}
