package mru.tsc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import mru.tsc.model.Figure;
import mru.tsc.model.Toy;

class FigureTest {

	@Test
	void test() {
		Figure f = new Figure(1452365872L, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		f.setSN(1125368135L);
		long test = f.getSN();
		long actual = 1125368135L;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test2() {
		Figure f = new Figure(1452365872, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		f.setName("Barbie Doll");
		String test = f.getName();
		String actual = "Barbie Doll";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test3() {
		Figure f = new Figure(1452365872, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		f.setBrand("Castle Toys");
		String test = f.getBrand();
		String actual = "Castle Toys";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test4() {
		Figure f = new Figure(1452365872, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		f.setPrice(55.99);
		double test = f.getPrice();
		double actual = 55.99;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test5() {
		Figure f = new Figure(1452365872, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		f.setAvaliableCount(4);
		int test = f.getAvaliableCount();
		int actual = 4;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test6() {
		Figure f = new Figure(1452365872, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		f.setAgeAppropriate("5+");
		String test = f.getAgeAppropriate();
		String actual = "5+";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test7() {
		Figure f = new Figure(1452365872, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		f.setClassification('D');
		char test = f.getClassification();
		char actual = 'D';
		Assert.assertEquals(test, actual);
	}
	@Test
	void test8() {
		Figure f = new Figure(1452365872, "Buzz Lightyear", "Warner Bros.", 32.99, 9, "6+", 'A');
		Assert.assertTrue(f instanceof Toy);
	}

}
