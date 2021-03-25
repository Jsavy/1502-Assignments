package mru.tsc.model;
/**
 * This class represents a subclass of Toy
 * 
 * @author Justin Savenko
 * @author Austin Thieu
 *
 */
public class Animal extends Toy{
	
	private char size; // the letter to identify the size of the animal
	private String material; // the material type of the animal
	
	/**
	 * The constructor for the Animal class that uses the super constructor for the appropriate variables
	 * 
	 * @param SN               the serial number of the animal
	 * @param name             the name of the animal
	 * @param brand            the brand name of the animal
	 * @param price            the price of the animal
	 * @param availiableCount  the available inventory for the animal
	 * @param ageAppropriate   the recommended age for the animal
	 * @param size             the size of the animal
	 * @param material         the material type for animal
	 */
	public Animal(String SN, String name, String brand, double price, int availiableCount, int ageAppropriate, String material, char size) {
		super(SN,name,brand,price,availiableCount,ageAppropriate);
		this.size = size;
		this.material = material;
	}
	/**
	 * getter method for the size of the animal
	 * 
	 * @return size  the size of the animal
	 */
	public char getSize() {
		return size;
	}
	/**
	 * setter method for the size of the animal
	 * 
	 * @param size   the size of the animal
	 */
	public void setSize(char size) {
		this.size = size;
	}
	
	public String getMaterial() {
		return material;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}
	/**
	 * formatted output for the Animal that uses the format from the super class to get info thats not toy specific
	 * 
	 * @return text  the formatted text for the animal
	 */
	public String format() {
		String text;
		text = super.format() + ";" + material + ";" + size;
		return text;
	}
	/**
	 * unformatted toString method to overide the default toString
	 * 
	 * @return text   the unformatted display for toString for animals
	 */
	public String toString() {
		String text;
		text = "Catagory: Animal " + super.toString() + "," + " Material: " + material + "," + " Size: " + size; 
		return text;
	}
}
