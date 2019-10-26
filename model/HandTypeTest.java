package model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HandTypeTest {
	// We define the hands using abbreviations. The code at the bottom
	// of this class can translate one of these strings into a card.
	// 
	// Another method takes a set of five cards, and translates the whole hand
	//
	//
	// Yet another method does this for a whole set of hands
	private static String[][] highCards = {
			{ "2S", "9C", "3H", "5D", "7H" },
			{ "7S", "5C", "AH", "JD", "6H" },
			{ "2S", "3S", "4S", "AD", "7S" },
			{ "AS", "KC", "QH", "JD", "TH" }
			};
	
	private static String[][] pairs = {
			{ "2S", "2C", "3H", "5D", "7H" },
			{ "2S", "AC", "3H", "5D", "AH" },
			{ "3S", "2C", "3H", "KD", "QH" },
			{ "9S", "2C", "2H", "5D", "7H" }
			};

	private static String[][] twoPairs = {
			{ "2S", "2C", "7H", "5D", "7H" },
			{ "2S", "AC", "5H", "5D", "AH" },
			{ "3S", "2C", "3H", "2D", "QH" },
			{ "9S", "2C", "2H", "5D", "5H" }
			};
	
	//New Test for three of a kind
	private static String[][] threeOfAKind = {
			{ "2S", "2C", "2H", "5D", "7H" },
			{ "2S", "5C", "5H", "5D", "AH" },
			{ "3S", "2C", "QH", "QD", "QH" },
			{ "9S", "2C", "5H", "5D", "5H" }
			};
	
	//New Test for straight
	private static String[][] isStraight = {
			{ "2S", "3C", "4H", "5D", "6H" },
			{ "3S", "4C", "5H", "6D", "7H" },
			{ "4S", "5C", "6H", "7D", "8H" },
			{ "4S", "5C", "6H", "7D", "8H" }
			};
	
	//New Test for flush
	private static String[][] isFlush = {
			{ "2S", "3S", "3S", "5S", "6S" },
			{ "3C", "3C", "5C", "6C", "QC" },
			{ "4H", "5H", "QH", "7H", "8H" },
			{ "4D", "5D", "6D", "AD", "AD" }
			};
	
	//New Test for flush
	private static String[][] isStraightFlush = {
			{ "2S", "3S", "4S", "5S", "6S" },
			{ "3C", "4C", "5C", "6C", "7C" },
			{ "5H", "6H", "7H", "8H", "9H" },
			{ "4D", "5D", "6D", "7D", "8D" }
			};
	
	// This is where we store the translated hands
	ArrayList<ArrayList<Card>> highCardHands;
	ArrayList<ArrayList<Card>> pairHands;
	ArrayList<ArrayList<Card>> twoPairHands;
	ArrayList<ArrayList<Card>> threeCardHands;
	ArrayList<ArrayList<Card>> straightHands;
	ArrayList<ArrayList<Card>> flushHands;
	ArrayList<ArrayList<Card>> straightFlushHands;
	
	/**
	 * The makeHands method is called before each test method,
	 * and prepares the translated hands. We recreate these for
	 * each test method, in case the test method damages the data.
	 */
	@Before
	public void makeHands() {
		highCardHands = makeHands(highCards);
		pairHands = makeHands(pairs);
		twoPairHands = makeHands(twoPairs);
		threeCardHands = makeHands(threeOfAKind);
		straightHands = makeHands(isStraight);
		flushHands = makeHands(isFlush);
		straightFlushHands = makeHands(isStraightFlush);
	}

	/**
	 * This is a test method for the isOnePair method in HandType.
	 * We expect all HighCard hands to be false, all OnePair hands to
	 * be true, all TwoPair hands to be true, etc.
	 */
	@Test
	public void testIsOnePair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isOnePair(hand)); // Two-pair contains a pair
		}
	}

	/**
	 * This is the test method for the isTwoPair in HandType.
	 */
	@Test
	public void testIsTwoPair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isTwoPair(hand));
		}
	}
	
	// Test if we have 3 Cards 
	@Test
	public void testIsThreeCards() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isThreeOfAKind(hand)); 
		}
		for (ArrayList<Card> hand : threeCardHands) {
			assertTrue(HandType.isThreeOfAKind(hand)); // should be true
		}
	}
	
	// Test if hand = straight 
	@Test
	public void testIsStraight() {
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isStraight(hand)); 
		}
		for (ArrayList<Card> hand : straightHands) {
			assertTrue(HandType.isStraight(hand)); // should be true
		}
	}
	
	// Test if hand = flush
	@Test
	public void testIsFlush() {
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isStraight(hand)); 
		}
		for (ArrayList<Card> hand : flushHands) {
			assertTrue(HandType.isFlush(hand)); // should be true
		}
	}
	
	// Test if hand = Straight Flush
	@Test
	public void testIsStraightFlush() {
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isStraightFlush(hand)); 
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isStraightFlush(hand)); // should be true
		}
	}
	
	/**
	 * Make an ArrayList of hands from an array of string-arrays
	 */
	private ArrayList<ArrayList<Card>> makeHands(String[][] handsIn) {
		ArrayList<ArrayList<Card>> handsOut = new ArrayList<>();
		for (String[] hand : handsIn) {
			handsOut.add(makeHand(hand));
		}
		return handsOut;
	}
	
	/**
	 * Make a hand (ArrayList<Card>) from an array of 5 strings
	 */
	private ArrayList<Card> makeHand(String[] inStrings) {
		ArrayList<Card> hand = new ArrayList<>();
		for (String in : inStrings) {
			hand.add(makeCard(in));
		}
		return hand;
	}
	
	/**
	 * Create a card from a 2-character String.
	 * First character is the rank (2-9, T, J, Q, K, A) 
	 * Second character is the suit (C, D, H, S)
	 * 
	 * No validation or error handling!
	 */
	private Card makeCard(String in) {
		char r = in.charAt(0);
		Card.Rank rank = null;
		if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
		else if (r == 'T') rank = Card.Rank.Ten;
		else if (r == 'J') rank = Card.Rank.Jack;
		else if (r == 'Q') rank = Card.Rank.Queen;
		else if (r == 'K') rank = Card.Rank.King;
		else if (r == 'A') rank = Card.Rank.Ace;
		
		char s = in.charAt(1);
		Card.Suit suit = null;
		if (s == 'C') suit = Card.Suit.Clubs;
		if (s == 'D') suit = Card.Suit.Diamonds;
		if (s == 'H') suit = Card.Suit.Hearts;
		if (s == 'S') suit = Card.Suit.Spades;

		return new Card(suit, rank);
	}
}
