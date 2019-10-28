package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.event.ActionListener;

import game.PokerGame;
import model.Card;
import model.DeckOfCards;
import model.Player;
import model.PokerGameModel;
import view.PlayerPane;
import view.PokerGameView;

public class PokerGameController{
	private PokerGameModel model;
	private PokerGameView view;
	
	public PokerGameController (PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.startGame.setOnAction(e -> view.stage.setScene(view.scene2));
		view.getGoBackButton().setOnAction(e -> view.stage.setScene(view.scene1));
		
		//view.twoPlayers().addActionListener(e -> addPlayer() );
		
	}
	
	//private void addPLayer () {
	//	PokerGame.NUM_PLAYERS++;
	//}
	
    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	}

    	model.getDeck().shuffle();
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int cardsRequired = PokerGame.NUM_PLAYERS * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
        		pp.updatePlayerDisplay();
        	}
    	} else {
    		model.getDeck().shuffle();
            //Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            //alert.showAndWait();
    	}
    }
}
