package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import model.DeckOfCards;

public class ControlArea extends HBox{
    private DeckLabel lblDeck = new DeckLabel();
    private Region spacer = new Region(); // Empty spacer
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    Button goBack = new Button("Back to Menu");
    
   

    public ControlArea() {
    	super(); // Always call super-constructor first !!
    	
    	this.getChildren().addAll(lblDeck, spacer, goBack, btnShuffle, btnDeal);
		lblDeck.getStyleClass().add("deckLabel"); //CSS
		goBack.getStyleClass().add("goBack-button"); //CSS
		btnShuffle.getStyleClass().add("shuffle-button"); //CSS
		btnDeal.getStyleClass().add("deal-button"); //CSS

        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
        
    }
    
    public void linkDeck(DeckOfCards deck) {
    	lblDeck.setDeck(deck);
    }
}
