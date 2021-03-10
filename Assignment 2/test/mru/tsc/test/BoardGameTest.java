package mru.tsc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Toy;

class BoardGameTest {

	@Test
	void test() {
		BoardGame b = new BoardGame(7123456789L, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setSN(8131615141L);
		long test = b.getSN();
		long actual = 8131615141L;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test2() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setName("Monopoly Canada Edition");
		String test = b.getName();
		String actual = "Monopoly Canada Edition";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test3() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setBrand("Castle Toys");
		String test = b.getBrand();
		String actual = "Castle Toys";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test4() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setPrice(19.42);
		double test = b.getPrice();
		double actual = 19.42;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test5() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setAvaliableCount(5);
		int test = b.getAvaliableCount();
		int actual = 5;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test6() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setAgeAppropriate("10+");
		String test = b.getAgeAppropriate();
		String actual = "10+";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test7() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setNumPlayers("2-8");
		String test = b.getNumPlayers();
		String actual = "2-8";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test8() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		b.setDesigner("Justin, Austin");
		String test = b.getDesigner();
		String actual ="Justin, Austin";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test9() {
		BoardGame b = new BoardGame(1123456789, "Monopoly", "Warner Bros.", 12.99, 2, "8+", "2-6", "John");
		Assert.assertTrue(b instanceof Toy);
	}

}
