package a2;

/**
 * Models an international crossing into Canada by land.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 14, 2021
 */
public class LandCrossing extends BorderCrossing {
	private String vehicleMake, vehicleModel, plateNumber;
	private int vehicleYear;

	/**
	 * Constructor which calls upon super to initialize BorderCrossing variables
	 * and adds on specific fields, {@code vehicleYear}, {@code vehicleMake},
	 * {@code vehicleModel}, and {@code plateNumber}.
	 * 
	 * @param origin          initialize in parent class
	 * @param destination     initialize in parent class
	 * @param isValidPassport initialize in parent class
	 * @param isCommercial    initialize in parent class
	 * @param traveler        initialize in parent class
	 * @param crossingId      initialize in parent class
	 * @param crossingTime    initialize in parent class
	 * @param vehicleYear
	 * @param vehicleMake     make of vehicle involved
	 * @param vehicleModel    model of vehicle involved
	 * @param plateNumber     license plate of vehicle involved
	 * @throws CrossingException custom exception handler for border crossings
	 */
	LandCrossing(String origin, String destination, boolean isValidPassport,
			boolean isCommercial, Traveler traveler, int crossingId,
			double crossingTime, int vehicleYear, String vehicleMake,
			String vehicleModel, String plateNumber) throws CrossingException {
		super(origin, destination, isValidPassport, isCommercial, traveler,
				crossingId, crossingTime);
		setVehicleMake(vehicleMake);
		setVehicleModel(vehicleModel);
		setVehicleYear(vehicleYear);
		setPlateNumber(plateNumber);
	}

	/**
	 * @return the make of the vehicle involved
	 */
	protected String getVehicleMake() {
		return vehicleMake;
	}

	/**
	 * @param vehicleMake set the make of the vehicle involved
	 */
	private void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	/**
	 * @return model of the vehicle involved
	 */
	protected String getVehicleModel() {
		return vehicleModel;
	}

	/**
	 * @param vehicleModel set the model of the vehicle involved
	 */
	private void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	/**
	 * @return license plate of vehicle involved
	 */
	protected String getPlateNumber() {
		return plateNumber;
	}

	/**
	 * Plate number must be a combination of 4 letters and 3 numbers to be
	 * valid.
	 * 
	 * @param plateNumber set the license plate of the vehicle involved
	 * @throws CrossingException to handle invalid license plates exception
	 */
	private void setPlateNumber(String plateNumber) throws CrossingException {
		int letters = 0, num = 0;
		for (char c : plateNumber.toCharArray()) {
			if (Character.isDigit(c))
				num++;
			if (Character.isLetter(c))
				letters++;
		}
		if (num != 3 || letters != 4)
			throw new CrossingException("Illegal Liscense Plate!");
		this.plateNumber = plateNumber;
	}

	/**
	 * @return the year the vehicle involved was made
	 */
	protected int getVehicleYear() {
		return vehicleYear;
	}

	/**
	 * Car cannot be made before 1980.
	 * 
	 * @param vehicleYear set the year the vehicle involved was made
	 * @throws CrossingException to handle exception when vehicle is too old
	 */
	private void setVehicleYear(int vehicleYear) throws CrossingException {
		if (vehicleYear < 1980)
			throw new CrossingException(
					"Illegal year - you need to buy a newer vehicle!");
		else
			this.vehicleYear = vehicleYear;
	}

	/**
	 * @return formatted string with toString of parent class as well as
	 *         specific land crossing information.
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"Crossing Method: Land\tVehicle Make: %-15s\tVehicle Model: %s\tLiscence Plate: %-8s\tVehicle Year: %d\n",
				vehicleMake, vehicleModel, plateNumber, vehicleYear);
	}

}
