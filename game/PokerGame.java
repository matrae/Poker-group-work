package game;

import javafx.application.Application;
import javafx.stage.Stage;
import controller.PokerGameController;
import model.PokerGameModel;
import view.PokerGameView;

public class PokerGame extends Application {
	public static int NUM_PLAYERS;
	
	
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;
	
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize the MVC components
    	model = new PokerGameModel();
    	view = new PokerGameView(primaryStage, model);
    	controller = new PokerGameController(model, view);
    }
}
