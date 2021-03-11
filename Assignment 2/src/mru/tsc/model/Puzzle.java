package mru.tsc.model;

public class Puzzle extends Toy {

	private char puzzleType; // the type of the puzzle
	/**
	 * The constructor for the puzzle class that uses the super constructor for the appropriate variables
	 * 
	 * @param SN                  the serial number of the puzzle
	 * @param name				  the name of the puzzle
	 * @param brand               the brand of the puzzle
	 * @param price               the price of the puzzle
	 * @param availiableCount     the inventory amount for the puzzle
	 * @param ageAppropriate      the recommended age for the puzzle
	 * @param puzzleType          the type of puzzle 
	 */
	public Puzzle(String SN, String name, String brand, double price, int availiableCount, int ageAppropriate, char puzzleType) {
		super(SN,name,brand,price,availiableCount,ageAppropriate);
		this.puzzleType = puzzleType;
	}
	/**
	 * getter method for the type of puzzle
	 * 
	 * @return puzzleType  the type of puzzle
	 */
	public char getPuzzleType() {
		return puzzleType;
	}
	/**
	 * setter method for the type of puzzle
	 * 
	 * @param puzzleType  the type of puzzle
	 */
	public void setPuzzleType(char puzzleType) {
		this.puzzleType = puzzleType;
	}
	/**
	 * formatted output for the puzzle that uses the format from the super class to get info thats not toy specific
	 * 
	 * @return text   the formatted out for the puzzle toy
	 */
	public String format() {
		String text;
		text = super.format() + ";" + puzzleType;
		return text;
	}
	/**
	 * unformatted toString method to overide the default toString
	 * 
	 * @return text   the unformatted display for toString for puzzles
	 */
	public String toString() {
		String text;
		text = "Catagory: Puzzle, " + super.toString() + "," + " Puzzle Type: " + puzzleType; 
		return text;
	}
}
