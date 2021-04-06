package mru.tsc.exceptions;
/**
 * This class is used to throw custom exceptions as per required for the assignment
 * 
 * @author Justin Savenko
 * @author Austin Thieu
 *
 */
public class PlayerException extends Exception{
	
	/**
	 * Constructor for creating a error message
	 * 
	 * @param error  the message the user will see
	 */
	public PlayerException(String error) {
		super("The minimum number of players exceeds the maximum" + error);
	}

}