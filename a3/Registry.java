package a3;

import java.util.ArrayList;

/**
 * A testing class to test functionality of all classes and the methods within.
 * 
 * @author Jade Wei <br>
 *         Date: August 2, 2021 <br>
 *         Student Number: 20224398
 * 
 */
public class Registry {

	/**
	 * Creates various instances of a person object and tests functionality of
	 * error handling, compareTo, and toString
	 */
	Registry() throws HRException {
		ArrayList<Person> people = populateValidPeople();
		System.out.println("Springfield University Records:\n");
		for (Person p : people) {
			System.out.println(p.toString() + "\n");
		}

		System.out.println("Age difference between Ava - 21 and Sam - 23 is "
				+ people.get(0).compareTo(people.get(2)));
		System.out.println("Age difference between Emma - 67 and Liam - 18 is "
				+ people.get(8).compareTo(people.get(9)));
		System.out
				.println("Age difference between Ava D - 21 and Ava I - 21 is "
						+ people.get(0).compareTo(people.get(1)));
		populateInvalidPeople(people);
	}

	/**
	 * Launches demo class
	 * 
	 * @param args
	 * @throws HRException to handle exceptions
	 */
	public static void main(String[] args) throws HRException {
		new Registry();
	}

	/**
	 * Populates the people ArrayList with a variety of the institution's staff
	 * and students
	 * 
	 * @param people ArrayList of person objects to add to
	 * @throws HRException to handle illegal values
	 */
	private ArrayList<Person> populateValidPeople() throws HRException {
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(new Undergraduate(21, "Ava", "Dom", 12345, 3, 5, 65000, true,
				"computer science", true));
		people.add(new Undergraduate(21, "Ava", "Int", 12345, 3, 5, 65000,
				false, "computer science", true));
		people.add(new Undergraduate(23, "Sam", "Dom", 54321, 2, 3, 70000, true,
				"medicine", true));
		people.add(new Undergraduate(23, "Sam", "Int", 54321, 2, 3, 70000,
				false, "medicine", true));

		people.add(new Graduate(27, "Mary", "Dom", 12394, 2, 4, 80000, true,
				"professional studies", 2000));
		people.add(new Graduate(27, "Mary", "Int", 12394, 2, 4, 80000, false,
				"professional studies", 2000));
		people.add(new Graduate(29, "Oliver", "Dom", 43321, 4, 5, 75000, true,
				"masters", 3000));
		people.add(new Graduate(29, "Oliver", "Int", 43321, 4, 5, 75000, false,
				"masters", 3000));

		people.add(new SupportStaff(67, "Emma", "K", 10032, 35,
				"arts and sciences", 40, 18));
		people.add(new SupportStaff(18, "Liam", "X", 93213, 1,
				"health sciences", 15, 15));

		people.add(new FacultyMember(25, "Jodi", "Grad", 32123, 0,
				"engineering", "graduate"));
		people.add(new FacultyMember(25, "Jodi", "Bach", 32123, 0,
				"engineering", "bachelor"));
		people.add(new FacultyMember(56, "Noah", "Grad", 23423, 9, "education",
				"graduate"));
		people.add(new FacultyMember(56, "Noah", "Bach", 23423, 9, "education",
				"bachelor"));
		return people;
	}

	/**
	 * Tests HRException class by passing in illegal values to the constructors.
	 * Each try/catch is there to ensure code keeps running even after an
	 * exception is thrown.
	 * 
	 * @param people ArrayList of person objects to add to
	 * @throws HRException to handle illegal values
	 */
	private void populateInvalidPeople(ArrayList<Person> people)
			throws HRException {
		System.out.println("\nDemonstrating Error Handling:");
		// Invalid Age
		try {
			people.add(new Undergraduate(-3, "Ava", "Dom", 12345, 3, 5, 65000,
					true, "computer science", true));
		} catch (HRException e) {
			System.out.println(e.getMessage());
		}
		// Invalid Course Number
		try {
			people.add(new Undergraduate(23, "Sam", "Int", 54321, 2, -3, 70000,
					false, "medicine", true));
		} catch (HRException e) {
			System.out.println(e.getMessage());
		}
		// Invalid Year of Study
		try {
			people.add(new Undergraduate(23, "Sam", "Dom", 54321, 99, 3, 70000,
					true, "medicine", true));
		} catch (HRException e) {
			System.out.println(e.getMessage());
		}
		// Invalid Scholarship Amount
		try {
			people.add(new Graduate(27, "Mary", "Dom", 12394, 2, 4, 50000, true,
					"professional studies", 2000));
		} catch (HRException e) {
			System.out.println(e.getMessage());
		}
		// Invalid Major / Program / Degree Type
		try {
			people.add(new Undergraduate(21, "Ava", "Dom", 12345, 3, 5, 65000,
					true, "cats", true));
		} catch (HRException e) {
			System.out.println(e.getMessage());
		}
		// Invalid Number of Years Worked
		try {
			people.add(new SupportStaff(18, "Liam", "X", 93213, 1000,
					"health sciences", 15, 15));
		} catch (HRException e) {
			System.out.println(e.getMessage());
		}
		// Invalid Base Pay
		try {
			people.add(new SupportStaff(18, "Liam", "X", 93213, 1,
					"health sciences", 15, 0));
		} catch (HRException e) {
			System.out.println(e.getMessage());
		}
	}
}
