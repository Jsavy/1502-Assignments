package mru.tsc.exceptions;
/**
 * This class is used to throw custom exceptions as per required for the assignment
 * 
 * @author Justin Savenko
 * @author Austin Thieu
 *
 */
public class ToyStoreException extends Exception{
	
	/**
	 * Constructor for creating a error message
	 * 
	 * @param error  the message the user will see
	 */
	public ToyStoreException(String error) {
		super(error);
	}

}
