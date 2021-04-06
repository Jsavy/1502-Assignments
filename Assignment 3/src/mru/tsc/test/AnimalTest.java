package mru.tsc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import mru.tsc.model.Animal;
import mru.tsc.model.Toy;

class AnimalTest {

	@Test
	void test() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setSN("3234567891");
		String test = a.getSN();
		String actual = "3234567891";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test2() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setName("Stuffed Giraffe");
		String test = a.getName();
		String actual = "Stuffed Giraffe";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test3() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setBrand("Castle Toys");
		String test = a.getBrand();
		String actual = "Castle Toys";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test4() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setPrice(4.99);
		double test = a.getPrice();
		double actual = 4.99;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test5() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setAvaliableCount(5);
		int test = a.getAvaliableCount();
		int actual = 5;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test6() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setAgeAppropriate(0);
		int test = a.getAgeAppropriate();
		int actual = 0;
		Assert.assertEquals(test, actual);
	}
	@Test
	void test7() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setSize('M');
		char test = a.getSize();
		char actual = 'M';
		Assert.assertEquals(test, actual);
	}
	@Test
	void test8() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		a.setMaterial("Wooden");
		String test = a.getMaterial();
		String actual = "Wooden";
		Assert.assertEquals(test, actual);
	}
	@Test
	void test9() {
		Animal a = new Animal("2146987123", "Stuffed Elephant", "Disney", 9.99, 9, 1, "Fabric", 'S');
		Assert.assertTrue(a instanceof Toy);
	}
}
