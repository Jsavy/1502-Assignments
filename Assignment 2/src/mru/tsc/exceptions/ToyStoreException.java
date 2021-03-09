package mru.tsc.exceptions;

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
