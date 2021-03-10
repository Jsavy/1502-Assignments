package mru.tsc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import mru.tsc.model.Puzzle;
import mru.tsc.model.Toy;

class PuzzleTest {

	@Test
	void test() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		p.setSN("5239874560");
		String test = p.getSN();
		String actual = 5239874560;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test2() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		p.setName("1000 Piece Jigsaw");
		String test = p.getName();
		String actual = "1000 Piece Jigsaw";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test3() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		p.setBrand("Castle Toys");
		String test = p.getBrand();
		String actual = "Castle Toys";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test4() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		p.setPrice(19.99);
		double test = p.getPrice();
		double actual = 19.99;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test5() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		p.setAvaliableCount(18);
		int test = p.getAvaliableCount();
		int actual = 18;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test6() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		p.setAgeAppropriate("5+");
		String test = p.getAgeAppropriate();
		String actual = "5+";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test7() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		p.setPuzzleType("Mechanical");
		String test = p.getPuzzleType();
		String actual = "Mechanical";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test8() {
		Puzzle p = new Puzzle("1234567890", "Jigsaw Puzzle Madness", "Warner Bros.", 24.99, 3, "3+", "Logic");
		Assert.assertTrue(p instanceof Toy);
	}

}
