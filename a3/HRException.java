package a3;

/**
 * Custom exception handling class for HR exceptions.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 28, 2021
 */
@SuppressWarnings("serial")
public class HRException extends Exception {
	/**
	 * Handles custom exceptions pertaining to the classes related to the people
	 * of Springfield University.
	 * 
	 * @param message sent back to user
	 */
	public HRException(String message) {
		super(message);
	}
}
