package model;

import java.util.ArrayList;

import model.Card.Suit;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }
    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    // Check if there are three of a kind in one set of cards 
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {   
        boolean threeFound = false;
        int iterations = 0;
        for (int i = 0; i < cards.size() - 1 && !threeFound; i++) {
            for (int j = i+1; j < cards.size() && !threeFound; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) {
                	iterations++; 
                }
                if(j == cards.size()-1 && iterations!=2) { 	
                	iterations = 0;
                }
                else if(j== cards.size()-1 && iterations >= 2) {
                    threeFound = true;
                }
            }
        }
        return threeFound;
    }
        
    public static boolean isStraight(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
    
    // Cards have to be same color 
    public static boolean isFlush(ArrayList<Card> cards) {
        boolean foundFlush = false;
        int counter = 0;
        for (int i = 0; i < cards.size() && !foundFlush; i++) {
            // Counter has to be either 0 or 5 for it to be a flush
        	if (cards.get(i).getSuit() == Suit.Diamonds || cards.get(i).getSuit() == Suit.Hearts) {
                counter++;
            } 
        }
    	if (counter == 0 || counter == 5) foundFlush = true;
        return foundFlush;
    }
    
    public static boolean isFullHouse(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
}
