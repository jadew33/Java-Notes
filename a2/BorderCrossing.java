package a2;

import java.util.List;

/**
 * Models border crossing events.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 14, 2021
 */
public class BorderCrossing {

	private String origin, destination;
	private boolean isValidPassport, isCommercial;
	private Traveler traveler;
	private int crossingId;
	private double crossingTime;
	/**
	 * Stores list of valid countries to use for comparison
	 */
	private static List<String> countries;

	/**
	 * @param origin          the country of origin of the border crossing event
	 * @param destination     the destination country of the border crossing
	 *                        event
	 * @param isValidPassport {@code true} if the traveler has a valid passport,
	 *                        {@code false} otherwise
	 * @param isCommercial    {@code true} if border crossing is for commercial
	 *                        purposes, {@code false} otherwise
	 * @param traveler        object representing person crossing the border
	 * @param crossingId      identification number for the border crossing
	 *                        event
	 * @param crossingTime    number of seconds spent at customs for screening
	 * @throws CrossingException handles custom exceptions related to border
	 *                           crossing
	 */
	protected BorderCrossing(String origin, String destination,
			boolean isValidPassport, boolean isCommercial, Traveler traveler,
			int crossingId, double crossingTime) throws CrossingException {
		countries = traveler.seedCountries();
		setOrigin(origin);
		setDestination(destination);
		setValidPassport(isValidPassport);
		setCommercial(isCommercial);
		setTraveller(traveler);
		setCrossingId(crossingId);
		setCrossingTime(crossingTime);
	}

	/**
	 * @return the country of origin of the border crossing event
	 */
	protected String getOrigin() {
		return origin;
	}

	/**
	 * First ensures that the origin country is in the list of countries. Then,
	 * if it is a land crossing, ensure that the origin is set to either Canada
	 * or the United States.
	 * 
	 * @param origin sets the country of origin of the border crossing event
	 * @throws CrossingException handles exceptions where the origin country is
	 *                           invalid
	 */
	private void setOrigin(String origin) throws CrossingException {
		if (!countries.contains(origin.toLowerCase()))
			throw new CrossingException("Invalid Country!");
		if (this instanceof LandCrossing
				&& !origin.toLowerCase().equals("canada")
				&& !origin.toLowerCase().equals("united states"))
			throw new CrossingException("Invalid Land Crossing Country!");
		this.origin = origin;
	}

	/**
	 * @return the destination country of the border crossing event
	 */
	protected String getDestination() {
		return destination;
	}

	/**
	 * First ensures that the destination country is in the list of countries.
	 * Then, if it is a land crossing, ensure that the destination is set to
	 * either Canada or the United States.
	 * 
	 * @param destination set the destination country of the border crossing
	 *                    event
	 */
	private void setDestination(String destination) throws CrossingException {
		if (!countries.contains(destination.toLowerCase()))
			throw new CrossingException("Invalid Country!");
		if (this instanceof LandCrossing
				&& !destination.toLowerCase().equals("canada")
				&& !destination.toLowerCase().equals("united states"))
			throw new CrossingException("Invalid Land Crossing Country");
		this.destination = destination;
	}

	/**
	 * @return {@code true} if the traveler has a valid passport, {@code false}
	 *         otherwise
	 */
	protected boolean isValidPassport() {
		return isValidPassport;
	}

	/**
	 * @param isValidPassport given {@code true} if the traveler has a valid
	 *                        passport, {@code false} otherwise
	 */
	private void setValidPassport(boolean isValidPassport) {
		this.isValidPassport = isValidPassport;
	}

	/**
	 * @return {@code true} if border crossing is for commercial purposes,
	 *         {@code false} otherwise
	 */
	protected boolean isCommercial() {
		return isCommercial;
	}

	/**
	 * @param isCommercial given {@code true} if border crossing is for
	 *                     commercial purposes, {@code false} otherwise
	 */
	private void setCommercial(boolean isCommercial) {
		this.isCommercial = isCommercial;
	}

	/**
	 * @return traveler object representing person crossing the border
	 */
	protected Traveler getTraveller() {
		return traveler;
	}

	/**
	 * @param traveler object representing person crossing the border
	 */
	private void setTraveller(Traveler traveler) {
		this.traveler = traveler;
	}

	/**
	 * @return identification number for the border crossing event
	 */
	protected int getCrossingId() {
		return crossingId;
	}

	/**
	 * @param crossingId set identification number for the border crossing event
	 */
	private void setCrossingId(int crossingId) {
		this.crossingId = crossingId;
	}

	/**
	 * @return number of seconds spent at customs for screening
	 */
	protected double getCrossingTime() {
		return crossingTime;
	}

	/**
	 * @param crossingTime set number of seconds spent at customs for screening
	 */
	private void setCrossingTime(double crossingTime) {
		this.crossingTime = crossingTime;
	}

	/**
	 * @return formatted string of common details for each crossing method.
	 */
	@Override
	public String toString() {
		return String.format(
				"Origin: %-10s\tDestination: %-13s\tValid Passport: %s\tCommercial Trip: %-8s\nTraveler Name: %s\tCrossing ID: %-15d\tCrossing Time: %.3fs\n",
				origin, destination, isValidPassport ? "True" : "False",
				isCommercial ? "True" : "False", traveler.getName(), crossingId,
				crossingTime);
	}

}
