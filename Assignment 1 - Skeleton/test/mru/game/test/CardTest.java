package mru.game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import mru.game.controller.Card;

class CardTest {

	@Test
	void test() {
		Card newCard = new Card(9, "Spades");
		Card newCard2 = new Card(9, "Clubs");
		String value = newCard.getSuit();
		String value2 = newCard2.getSuit();
		Assert.assertNotEquals(value, value2);
	}
	@Test
	void test2() {
		Card newCard = new Card(10, "Diamonds");
		Card newCard2 = new Card(10, "Clubs");
		int value = newCard.getRank();
		int value2 = newCard2.getRank();
		Assert.assertEquals(value, value2);
	}
	@Test
	void test3() {
		Card newCard = new Card(2, "Clubs");
		String test = "Clubs";
		String reset = "Diamonds";
		newCard.setSuit(reset);
		Assert.assertEquals(newCard.getSuit(), reset);
	}
	@Test
	void test4() {
		Card newCard = new Card(5, "Spades");
		int newValue = 7;
		newCard.setRank(newValue);
		Assert.assertEquals(newCard.getRank(), newValue);
	}
	@Test
	void test5() {
		Card newCard = new Card(4, "Hearts");
		Card testCard = new Card(4, "Hearts");
		testCard.setSuit("Diamond");
		testCard.setRank(8);
		Assert.assertNotEquals(newCard, testCard);
	}

}
