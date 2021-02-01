package mru.game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import mru.game.model.Player;

class PlayerTest {

	@Test
	void test() {
		String name = "Justin";
		int score = 100;
		int wins = 0;
		Player tester = new Player(name,score,wins);
		String getter = tester.getName();
		Assert.assertEquals(getter, name);
		
	}
	
	@Test
	void test2() {
		String name = "Austin";
		int score = 100;
		int wins = 0;
		Player tester = new Player(name,score,wins);
		int getter = tester.getScore();
		Assert.assertEquals(getter, score);
	}
	
	@Test
	void test3() {
		String name = "Dustin";
		int score = 1763;
		int wins = 21;
		Player tester = new Player(name,score,wins);
		int getter = tester.getWins();
		Assert.assertEquals(getter, wins);
	}
	
	@Test
	void test4() {
		String name = "Rustin";
		int score = 100;
		int wins = 0;
		String setterTest = "Justin";
		String expected = "Justin";
		Player tester = new Player(name,score,wins);
		tester.setName(setterTest);
		Assert.assertEquals(expected, setterTest);
	}
	
	@Test
	void test5() {
		String name = "Faustin";
		int score = 100;
		int wins = 0;
		int setterTest = 250;
		int expected = 250;
		Player tester = new Player(name,score,wins);
		tester.setScore(setterTest);
		Assert.assertEquals(expected, setterTest);
	}
	
	@Test
	void test6() {
		String name = "Augustus";
		int score = 250;
		int wins = 0;
		int setterTest = 1;
		int expected = 1;
		Player tester = new Player(name,score,wins);
		tester.setWins(setterTest);
		Assert.assertEquals(expected, setterTest);
	}

}
