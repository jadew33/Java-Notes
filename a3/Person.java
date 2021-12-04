package a3;

/**
 * Root class for all people at Springfield University, including staff and
 * students. Accessors and mutators for fields that are not used in subclasses
 * are left private, which could be changed to protected if needed.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 28, 2021
 */
public class Person {
	int age;
	String firstName, lastName;

	/**
	 * Creates a person object.
	 * 
	 * @param age       age of the person
	 * @param firstName first name of the person
	 * @param lastName  last name of the person
	 * @throws HRException to handle illegal values
	 */
	Person(int age, String firstName, String lastName) throws HRException {
		setAge(age);
		setFirstName(firstName);
		setLastName(lastName);
	}

	/**
	 * @return the age of the person
	 */
	private int getAge() {
		return age;
	}

	/**
	 * @param age age of the person
	 * @throws HRException to handle illegal ages
	 */
	private void setAge(int age) throws HRException {
		if (age < 15)
			throw new HRException("Age should be greater than or equal to 15.");
		else
			this.age = age;
	}

	/**
	 * @return the first name of the person
	 */
	private String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName first name of the person
	 */
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the last name of the person
	 */
	private String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName last name of the person
	 */
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method that compares the "age" of two instances of a Person object. This
	 * method is tested in the registry class.
	 * 
	 * @param p person to compare against
	 * @return age difference between the two people. Positive if this person's
	 *         age is greater than person p's age; negative if age is smaller;
	 *         and 0 if the ages are equal
	 */
	protected int compareTo(Person p) {
		return this.getAge() - p.getAge();
	}

	/**
	 * @return formatted string of results
	 */
	@Override
	public String toString() {
		return String.format(
				"First Name: %-15s\tLast Name: %-20s\tAge: %-20d\t", firstName,
				lastName, age);
	}

}
