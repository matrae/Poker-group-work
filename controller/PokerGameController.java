package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.event.ActionListener;
import java.io.Console;

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
		
		//set Buttons to playerNumbers
		view.twoPlayers.setOnAction(e -> setPlayers(2));
		view.threePlayers.setOnAction(e -> setPlayers(3));
		view.fourPlayers.setOnAction(e -> setPlayers(4));
		
		//start game and go back buttons
		view.startGame.setOnAction(e ->	view.stage.setScene(view.scene2));		
		view.getGoBackButton().setOnAction(e -> view.stage.setScene(view.scene1));
		
		
	}
	
	
	public void setPlayers(int i) {
		PokerGame.NUM_PLAYERS = i;
		model.createPLayer();
		view.displayPlayer();
	}
	
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
    		
        	//Every single hand gets evaluated here and display is updated
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
    		//Set winner to zero 
			int winner = 5;
    		for(int i = 0; i < PokerGame.NUM_PLAYERS-1; i++) {
     			Player o = model.getPlayer(i);
    			Player p = model.getPlayer(i+1);
    			if (p.compareTo(o) < 0) {
    				winner = i;
    			} else if (p.compareTo(o) > 0) {
    				winner = i+1;
    			} else {
    				winner = 5;
    			}
    		}
    		if (winner != 5) {
				Player p = model.getPlayer(winner);
				p.updateScore();
				PlayerPane pp = view.getPlayerPane(winner);
        		pp.updatePlayerDisplay();
    		}
			

    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }
}
