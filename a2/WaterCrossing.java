package a2;

/**
 * Models an international crossing into Canada by water.
 * 
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 14, 2021
 */
public class WaterCrossing extends BorderCrossing {

	private boolean isPleasureCraft;
	private double vesselLength;

	/**
	 * Constructor which calls upon super to initialize BorderCrossing variables
	 * and adds on specific fields, {@code isPleasureCraft} and
	 * {@code vesselLength}.
	 * 
	 * @param origin          initialize in parent class
	 * @param destination     initialize in parent class
	 * @param isValidPassport initialize in parent class
	 * @param isCommercial    initialize in parent class
	 * @param traveler        initialize in parent class
	 * @param crossingId      initialize in parent class
	 * @param crossingTime    initialize in parent class
	 * @param isPleasureCraft given {@code true} if the crossing was a pleasure
	 *                        craft and {@code false} if it was not
	 * @param vesselLength    length of the vessel in meters
	 * @throws CrossingException custom exception handler for border crossings
	 */
	WaterCrossing(String origin, String destination, boolean isValidPassport,
			boolean isCommercial, Traveler traveler, int crossingId,
			double crossingTime, boolean isPleasureCraft, double vesselLength)
			throws CrossingException {
		super(origin, destination, isValidPassport, isCommercial, traveler,
				crossingId, crossingTime);
		setPleasureCraft(isPleasureCraft);
		setVesselLength(vesselLength);
	}

	/**
	 * @return {@code true} if the crossing was a pleasure craft and
	 *         {@code false} if it was not
	 */
	protected boolean isPleasureCraft() {
		return isPleasureCraft;
	}

	/**
	 * @param isPleasureCraft given {@code true} if the crossing was a pleasure
	 *                        craft and {@code false} if it was not
	 */
	private void setPleasureCraft(boolean isPleasureCraft) {
		this.isPleasureCraft = isPleasureCraft;
	}

	/**
	 * @return length of the vessel in meters
	 */
	protected double getVesselLength() {
		return vesselLength;
	}

	/**
	 * @param vesselLength the length of the vessel in meters
	 */
	private void setVesselLength(double vesselLength) {
		this.vesselLength = vesselLength;
	}

	/**
	 * @return formatted string with toString of parent class as well as
	 *         specific water crossing information.
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"Crossing Method: Water\tPleasure Craft: %-15s\tVessel Length: %.2fm\n",
				isPleasureCraft ? "True" : "False", vesselLength);
	}
}
