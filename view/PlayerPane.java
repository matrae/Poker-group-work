package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Card;
import model.HandType;
import model.Player;

public class PlayerPane extends VBox {
	private Player score;
	
	private Label lblName = new Label();
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");
    private Label scoreLabel = new Label("Score"); //Create score label
    private Label actualScore = new Label("0"); 
    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
       
        this.getStyleClass().add("player"); // CSS style class
        lblName.getStyleClass().add("playerLabel");
        lblEvaluation.getStyleClass().add("evaluationLabel");
        scoreLabel.getStyleClass().add("scoreLabel");
        actualScore.getStyleClass().add("actualScore");
        
        
        // Add child nodes
        this.getChildren().addAll(lblName, hboxCards, lblEvaluation,scoreLabel, actualScore);
        
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
            lblCard.getStyleClass().add("cardLabel");
        }
        
        // Add spaces between cards
        hboxCards.setSpacing(7);
  
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    	updatePlayerDisplay(); // Immediately display the player information
    }
    
    public void updatePlayerDisplay() {
    	lblName.setText(player.getPlayerName());
    	actualScore.setText(player.getScore());
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		Card card = null;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
    		cl.setCard(card);
    		HandType evaluation = player.evaluateHand();
    		if (evaluation != null) 
    			lblEvaluation.setText(evaluation.toString());
    		else 
    			lblEvaluation.setText("--");
    	}
    }
}
