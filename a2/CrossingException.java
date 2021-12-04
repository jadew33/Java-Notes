package a2;

/**
 * Custom exception handling class for border crossing exceptions.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 14, 2021
 */
@SuppressWarnings("serial")
public class CrossingException extends Exception {

	/**
	 * Handles custom exceptions pertaining to the BorderCrossing class
	 * 
	 * @param message sent back to user
	 */
	public CrossingException(String message) {
		super(message);
	}

}
