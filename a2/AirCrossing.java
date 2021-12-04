package a2;

/**
 * Models an international border crossing into Canada by air.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 14, 2021
 */
public class AirCrossing extends BorderCrossing {

	private boolean isLayover;
	private String airportName;

	/**
	 * Constructor which calls upon super to initialize BorderCrossing variables
	 * and adds on specific fields, {@code isLayover} and {@code airportName}.
	 * 
	 * @param origin          initialize in parent class
	 * @param destination     initialize in parent class
	 * @param isValidPassport initialize in parent class
	 * @param isCommercial    initialize in parent class
	 * @param traveler        initialize in parent class
	 * @param crossingId      initialize in parent class
	 * @param crossingTime    initialize in parent class
	 * @param isLayover       {@code true} if crossing event is a layover and
	 *                        {@code false} if not
	 * @param airportName     name of airport where crossing event occurred
	 * @throws CrossingException custom exception handler for border crossings
	 */
	AirCrossing(String origin, String destination, boolean isValidPassport,
			boolean isCommercial, Traveler traveler, int crossingId,
			double crossingTime, boolean isLayover, String airportName)
			throws CrossingException {
		super(origin, destination, isValidPassport, isCommercial, traveler,
				crossingId, crossingTime);
		setLayover(isLayover);
		setAirportName(airportName);
	}

	/**
	 * @return {@code true} if the crossing event takes place at a layover and
	 *         {@code false} if it does not
	 */
	protected boolean isLayover() {
		return isLayover;
	}

	/**
	 * @param isLayover given {@code true} if the crossing event takes place at
	 *                  a layover and {@code false} if it does not
	 */
	private void setLayover(boolean isLayover) {
		this.isLayover = isLayover;
	}

	/**
	 * @return string containing the airport name
	 */
	protected String getAirportName() {
		return airportName;
	}

	/**
	 * @param airportName sets name of the airport
	 */
	private void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	/**
	 * @return formatted string with toString of parent class as well as
	 *         specific air crossing information.
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"Crossing Method: Air\tLayover: %-15s\tAirport Name: %s\n",
				isLayover ? "True" : "False", airportName);
	}
}
