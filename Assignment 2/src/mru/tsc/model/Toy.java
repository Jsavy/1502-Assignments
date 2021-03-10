package mru.tsc.model;

public abstract class Toy {
	
	private long SN; // the serial number of the toy
	private String name; // the name of the toy
	private String brand; // the brand of the toy
	private double price; // the price of the toy
	private int avaliableCount; // the inventory level of the specific toy
	private int ageAppropriate; // the recommended age for the toy
	/**
	 * A constructor for the Toy class that brings in all the required fields for the specific toy
	 * 
	 * @param SN                  the serial number of the toy
	 * @param name                the name of the toy
	 * @param brand               the brand of the toy
	 * @param price               the price of the toy
	 * @param availiableCount     the amount of inventory of the item
	 * @param ageAppropriate      the recommended age for the toy
	 */
	public Toy(long SN, String name, String brand, double price, int availiableCount, int ageAppropriate) {
		this.SN = SN;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.avaliableCount = availiableCount;
		this.ageAppropriate = ageAppropriate;
	}
	/**
	 * getter method for the serial number 
	 * 
	 * @return SN   the serial number of the toy
	 */
	public long getSN() {
		return SN;
	}
	/**
	 * setter method for the serial number
	 * 
	 * @param sN   the serial number of the toy
	 */
	public void setSN(long sN) {
		SN = sN;
	}
	/**
	 * getter method for the brand of toy
	 * 
	 * @return brand   the brand of the toy
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * setter method for the brand of toy
	 * 
	 * @param brand  the brand of the toy
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * getter method for the price of toy
	 * 
	 * @return price  the price of the toy
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * setter method for the price of toy
	 * 
	 * @param price  the price of the toy
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * getter method for the inventory level of the toy
	 * 
	 * @return avaliableCount  the amount of inventory for the toy
	 */
	public int getAvaliableCount() {
		return avaliableCount;
	}
	/**
	 * setter method for the inventory level of the toy
	 * 
	 * @param avaliableCount  the amount of inventory for the toy
	 */
	public void setAvaliableCount(int avaliableCount) {
		this.avaliableCount = avaliableCount;
	}
	/**
	 * getter method for the recommended age for the toy
	 * 
	 * @return ageAppropriate  the recommended age for the use of the toy
	 */
	public int getAgeAppropriate() {
		return ageAppropriate;
	}
	/**
	 * setter method for the recommended age for the toy
	 * 
	 * @param ageAppropriate  the recommended age for the use of the toy
	 */
	public void setAgeAppropriate(int ageAppropriate) {
		this.ageAppropriate = ageAppropriate;
	}
	/**
	 * getter method for the name of the toy
	 * 
	 * @return name  the name of the toy
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter method for the name of the toy
	 * 
	 * @param name  the name of the toy
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * formatted output method that follows the file format
	 * 
	 * @return text  the formatted text of the toy for the file
	 */
	public String format() {
		String text;
		text = SN + ";" + name + ";" + brand + ";" + price + ";" + avaliableCount + ";" + ageAppropriate;
		return text;
	}
	/**
	 * unformatted toString method to overide the default toString
	 * 
	 * @return text   the unformatted display for toString
	 */
	public String toString() {
		String text;
		text = "Serial Number: " + SN + "," + " Name: " + name + "," + " Brand: " + brand + "," + 
		" Price: " + price + "," + " Available Count: " + avaliableCount + "," + " Age Appropriate: " + ageAppropriate;
		return text;
	}
}
