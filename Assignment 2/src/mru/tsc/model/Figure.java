package mru.tsc.model;

public class Figure extends Toy {
	
	private char classification; // the classification used for the type of figure
	/**
	 * The constructor for the figure class that uses the super constructor for the appropriate variables
	 * 
	 * @param SN                 the serial number of the figure
	 * @param name               the name of the figure
	 * @param brand              the brand of the figure
	 * @param price              the price of the figure
	 * @param availiableCount    the amount of inventory left for the figure
	 * @param ageAppropriate     the recommended age for the figure
	 * @param classification     the classification type of the figure
	 */
	public Figure(String SN, String name, String brand, double price, int availiableCount, int ageAppropriate, char classification) {
		super(SN,name,brand,price,availiableCount,ageAppropriate);
		this.classification = classification;
	}
	/**
	 * getter method for the classification type of figure
	 * 
	 * @return classification  the classification type of the figure
	 */
	public char getClassification() {
		return classification;
	}
	/**
	 * setter method for the classification type of figure
	 * 
	 * @param classification  the classification type of the figure
	 */
	public void setClassification(char classification) {
		this.classification = classification;
	}
	/**
	 * formatted output for the figure that uses the format from the super class to get info thats not toy specific
	 * 
	 * @return text  the formatted out for the figure
	 */
	public String format() {
		String text;
		text = super.format() + ";" + classification;
		return text;
	}
	/**
	 * unformatted toString method to overide the default toString
	 * 
	 * @return text   the unformatted display for toString for figures
	 */
	public String toString() {
		String text;
		text = "Catagory: Figure, " + super.toString() + "," + "Classification Type: " + classification;
		return text;
	}
}
