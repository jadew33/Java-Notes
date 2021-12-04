package a2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class that represents a collection of border crossing events and their
 * related information.
 * 
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 31, 2021
 */
public class Registry {

	private ArrayList<BorderCrossing> borderCrossings;
	private int numCrossings;

	/**
	 * Testing code, which creates several instances of each type of class
	 * (LandCrossing, AirCrossing, and WaterCrossing), stores these instances
	 * into an ArrayList collection, and demonstrates the functionality of each
	 * of the required methods including toString, addCrossing, printRegistry,
	 * averageTime. <br>
	 * Each try/catch is to ensure that the entire code block runs without
	 * jumping out.
	 * 
	 * @throws CrossingException to catch any invalid objects
	 */
	Registry() throws CrossingException {
		borderCrossings = new ArrayList<BorderCrossing>();
		numCrossings = 0;

		List<Traveler> travelers = new ArrayList<Traveler>();
		travelers.add(new Traveler("Jane", "France", 30, true));
		travelers.add(new Traveler("Adam", "China", 20, false));
		// Error - Traveler age < 0 or age > 120
		try {
			travelers.add(new Traveler("Mary", "Thailand", 1000, false));
		} catch (CrossingException e) {
			System.out.println(e.getMessage());
		}
		// Error - Traveler invalid country
		try {
			travelers.add(new Traveler("John", "IDONTEXIST", 100, false));
		} catch (CrossingException e) {
			System.out.println(e.getMessage());
		}

		// Valid travelers are printed out to demonstrate the traveler
		// toString()
		System.out.println("\nTravelers After Error Checking:");
		for (Traveler t : travelers) {
			System.out.println(t.toString());
		}
		System.out.println();

		addCrossing(new BorderCrossing("Canada", "Spain", true, true,
				travelers.get(0), 98321, 150.34));
		addCrossing(new LandCrossing("United States", "Canada", true, false,
				travelers.get(1), 82134, 200, 2000, "Toyota", "SUV",
				"A1B2 C3D"));
		addCrossing(new LandCrossing("Canada", "United States", false, true,
				travelers.get(1), 34623, 298, 1995, "Jeep", "Van", "TAXI111"));
		addCrossing(new WaterCrossing("Japan", "China", true, true,
				travelers.get(0), 92222, 300, true, 100));
		addCrossing(new WaterCrossing("Austria", "Australia", false, false,
				travelers.get(0), 33323, 223, false, 150));
		addCrossing(new AirCrossing("Switzerland", "Egypt", true, true,
				travelers.get(1), 12393, 175.33, true, "XYZ"));
		addCrossing(new AirCrossing("Bahamas", "Vatican City", false, false,
				travelers.get(1), 98341, 274.3, false, "YYZ"));
		// Error - land crossing with vehicle year < 1980
		try {
			addCrossing(new LandCrossing("United States", "Canada", true, false,
					travelers.get(1), 82134, 200, 1800, "Toyota", "SUV",
					"A1B2 C3D"));
		} catch (CrossingException e) {
			System.out.println(e.getMessage());
		}
		// Error - land crossing invalid plate number
		try {
			addCrossing(new LandCrossing("United States", "Canada", true, false,
					travelers.get(1), 82134, 200, 2000, "Toyota", "SUV",
					"FAKEEE"));
		} catch (CrossingException e) {
			System.out.println(e.getMessage());
		}
		// Error - Border crossing invalid country
		try {
			addCrossing(new BorderCrossing("Mars", "Spain", true, true,
					travelers.get(0), 98321, 120.3));
		} catch (CrossingException e) {
			System.out.println(e.getMessage());
		}
		// Error - land crossing origin/destination != Canada and US
		try {
			addCrossing(new LandCrossing("United States", "Thailand", true,
					false, travelers.get(1), 82134, 200, 2000, "Toyota", "SUV",
					"A1B2 C3D"));
		} catch (CrossingException e) {
			System.out.println(e.getMessage());
		}

		printRegistry();
		System.out.printf("Average time spent at customs: %.3fs",
				averageTime());
	}

	/**
	 * Adds a BorderCrossing object to the registry, increasing the total amount
	 * of crossings
	 * 
	 * @param crossing object to be added
	 */
	private void addCrossing(BorderCrossing crossing) {
		borderCrossings.add(crossing);
		numCrossings++;
	}

	/**
	 * Prints out all of the information for the BorderCrossing events
	 */
	private void printRegistry() {
		System.out.println("\nBorder Crossing Information:\n");
		for (BorderCrossing crossing : borderCrossings)
			System.out.println(crossing.toString());
		System.out.println("Number of Crossings: " + numCrossings);
	}

	/**
	 * Calculates the average time spent at customs for screening for all border
	 * crossing events in the border crossing collection ArrayList in seconds
	 * 
	 * @return The average time calculated by the total time taken divided by
	 *         number of crossings
	 */
	private double averageTime() {
		double total = 0;
		for (BorderCrossing crossing : borderCrossings)
			total += crossing.getCrossingTime();
		return total / numCrossings;
	}

	/**
	 * Main method which runs the demo class
	 * 
	 * @param args
	 * @throws CrossingException to catch invalid object creation attempts
	 */
	public static void main(String[] args) throws CrossingException {
		new Registry();
	}
}
