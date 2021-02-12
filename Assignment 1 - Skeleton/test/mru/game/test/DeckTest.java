package mru.game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import mru.game.controller.*;

class DeckTest {

	private static final String Card = null;
	@Test
	void test() {
		CardDeck deck;
		CardDeck deck2;
		deck = new CardDeck();
		deck2 = new CardDeck();
		Assert.assertNotSame(deck, deck2);	
	}
	@Test
	void test2() {
		CardDeck deck = new CardDeck();
		CardDeck decktest = new CardDeck();
		Card currentCard = deck.getDeck().remove(0);
		System.out.println(currentCard);
		System.out.println(deck.getDeck().size());
		System.out.println(decktest.getDeck().size());
		
	}
	@Test
	void test3() {
		CardDeck deck = new CardDeck();
		Card currentCard = deck.getDeck().remove(0);
		Card secondCard = deck.getDeck().remove(0);
		Assert.assertNotSame(currentCard, secondCard);
		
	}
	@Test
	void test4() {
		CardDeck deck = new CardDeck();
		Card testCard = deck.getDeck().remove(0);
		System.out.println(testCard.getRank());
		System.out.println(testCard.getSuit());
	}
	@Test
	void test5() {
		CardDeck deck = new CardDeck();
		Card topCard = deck.getDeck().remove(0);
		Assert.assertNotNull(topCard);
	}
}
