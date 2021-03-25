package mru.tsc.model;
/**
 * This class represents a subclass of Toy
 * 
 * @author Justin Savenko
 * @author Austin Thieu
 *
 */
public class BoardGame extends Toy {
	
	private String numPlayers; // the required minimum number of players and maximum
	private String designer; // the names of the designers of the board game
	/**
	 * The constructor for the BoardGame class that uses the super constructor for the appropriate variables
	 * 
	 * @param SN                 the serial number of the board game
	 * @param name               the name of the board game
	 * @param brand              the brand name of the board game
	 * @param price              the price of the board game
	 * @param availiableCount    the available inventory for the board game
	 * @param ageAppropriate     the recommended age for the board game
	 * @param numPlayers         the minimum and maximum number of players for the board game
	 * @param designer           the names of who designed the board game
	 */
	public BoardGame(String SN, String name, String brand, double price, int availiableCount, int ageAppropriate, String numPlayers, String designer) {
		super(SN,name,brand,price,availiableCount,ageAppropriate);
		this.numPlayers = numPlayers;
		this.designer = designer;
	}
	/**
	 * getter method for the minimum and maximum number of players for the board game
	 * 
	 * @return numPlayers   the min and max number of players
	 */
	public String getNumPlayers() {
		return numPlayers;
	}
	/**
	 * setter method for the minimum and maximum number of player for the board game
	 * 
	 * @param numPlayers  the min and max number of players
	 */
	public void setNumPlayers(String numPlayers) {
		this.numPlayers = numPlayers;
	}
	/**
	 * getter method for the names of the designers for the board game
	 * 
	 * @return designer   the names of the designer for the board game
	 */
	public String getDesigner() {
		return designer;
	}
	/**
	 * setter method for the names of the designers for the board game
	 * 
	 * @param designer  the names of the designer for the board game
	 */
	public void setDesigner(String designer) {
		this.designer = designer;
	}
	/**
	 * formatted output for the BoardGame that uses the format from the super class to get info thats not toy specific
	 * 
	 * @return text  the formatted text for the board game
	 */
	public String format() {
		String text;
		text = super.format() + ";" + numPlayers + ";" + designer;
		return text;
	}
	/**
	 * unformatted toString method to overide the default toString
	 * 
	 * @return text   the unformatted display for toString for board games 
	 */
	public String toString() {
		String text;
		text = "Catagory: BoardGame, " + super.toString() + "," + " Number of players: " + numPlayers + "," + " Designer Names: " + designer;
		return text;
	}
}
