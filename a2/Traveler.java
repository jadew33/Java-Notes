package a2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Models a Traveler who is traveling into Canada.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 14, 2021
 *
 */
public class Traveler {
	private String name, citizenship;
	private int age;
	private boolean hasConvictions;

	/**
	 * Constructor initializing a traveler.
	 * 
	 * @param name           name of traveler
	 * @param citizenship    citizenship of the traveler
	 * @param age            age of the traveler
	 * @param hasConvictions {@code true} if the traveler has previous customs
	 *                       violations and {@code false} if not
	 * @throws CrossingException handles custom border crossing exceptions
	 */
	Traveler(String name, String citizenship, int age, boolean hasConvictions)
			throws CrossingException {
		setName(name);
		setCitizenship(citizenship);
		setAge(age);
		setHasConvictions(hasConvictions);
	}

	/**
	 * @return name of traveler
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name set name of traveler
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @return citizenship of the traveler
	 */
	protected String getCitizenship() {
		return citizenship;
	}

	/**
	 * @param citizenship set citizenship of traveler if it is valid
	 * @throws CrossingException to handle creating a traveler with invalid
	 *                           country
	 */
	private void setCitizenship(String citizenship) throws CrossingException {
		List<String> countries = seedCountries();
		if (!countries.contains(citizenship.toLowerCase()))
			throw new CrossingException("Invalid Country!");
		this.citizenship = citizenship;
	}

	/**
	 * @return age of traveler
	 */
	protected int getAge() {
		return age;
	}

	/**
	 * @param age set age of traveler if it is valid
	 * @throws CrossingException to handle creating a traveler with invalid age
	 */
	private void setAge(int age) throws CrossingException {
		if (age < 0 || age > 120)
			throw new CrossingException("Illegal age!");
		else
			this.age = age;
	}

	/**
	 * @return {@code true} if the traveler has previous customs violations and
	 *         {@code false} if not
	 */
	protected boolean isHasConvictions() {
		return hasConvictions;
	}

	/**
	 * @param hasConvictions set to {@code true} if the traveler has previous
	 *                       customs violations and {@code false} if not
	 */
	private void setHasConvictions(boolean hasConvictions) {
		this.hasConvictions = hasConvictions;
	}

	/**
	 * @return formatted string of each traveler
	 */
	@Override
	public String toString() {
		return String.format(
				"Name: %s\tCitizenship: %s\tAge: %d\t\tPast Convictions: %s\t",
				name, citizenship, age, hasConvictions ? "True" : "False");
	}

	/**
	 * Reads in file containing valid countries and stores them in a list.
	 * 
	 * @return list storing the valid countries
	 * @throws CrossingException in the case that the file containing valid
	 *                           countries cannot be found
	 */
	protected final List<String> seedCountries() throws CrossingException {
		List<String> countries = new ArrayList<String>();
		try {
			InputStream file = this.getClass()
					.getResourceAsStream("Countries.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext())
				countries.add(scanner.nextLine().toLowerCase());
			scanner.close();
		} catch (Exception e) {
			throw new CrossingException("File Not Found!");
		}
		return countries;
	}
}
