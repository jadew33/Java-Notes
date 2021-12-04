package a1;

/**
 * 
 * Scoring Methods for the game Zanzibar. Zanzibar is the best outcome, and
 * given the highest arbituary value for comparison. Three of a kind is the next
 * best, with sequence following it. If none of those outcomes are acheived, the
 * player gets points according to the sum of their roll (Points Total method).
 * 
 * @author Jade Wei <br>
 *         Date: July 15, 2021 <br>
 *         Student Number: 20224398
 *
 */
public enum ScoringMethod {
	ZANZIBAR(4), THREEOFKIND(3), SEQUENCE(2), POINTSTOTAL(1);

	private int value;

	/**
	 * Constructor to initialize the arbitrary assigned values to each scoring
	 * method
	 * 
	 * @param i is the value given to each scoring method
	 */
	ScoringMethod(int i) {
		this.value = i;
	}

	/**
	 * 
	 * @return value for comparison among outcomes to determine the winning
	 *         outcome
	 */
	protected int getValue() {
		return value;
	}
}