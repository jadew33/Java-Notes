package a3;

/**
 * Extends person class to model a student of Springfield University. Accessors
 * and mutators for fields that are not used in subclasses are left private,
 * which could be changed to protected if needed.
 * 
 * @author Jade Wei <br>
 *         Student Number: 20224398 <br>
 *         Date: July 28, 2021
 */
public abstract class Student extends Person {
	private int studentId, studyYear, numCourses;
	private double scholarship;
	private boolean isDomestic;

	/**
	 * Creates new student with age, firstName, and lastName passed to the
	 * parent class to be initialized.
	 * 
	 * @param age         passed to parent
	 * @param firstName   passed to parent
	 * @param lastName    passed to parent
	 * @param studentId   the student’s unique identifier
	 * @param studyYear   the student’s year of study
	 * @param numCourses  the number of courses a student is taking
	 * @param scholarship the amount of a student’s scholarship
	 * @param isDomestic  {@code true} if student is a domestic student and
	 *                    {@code false} otherwise
	 * @throws HRException to handle any illegal values
	 */
	Student(int age, String firstName, String lastName, int studentId,
			int studyYear, int numCourses, double scholarship,
			boolean isDomestic) throws HRException {
		super(age, firstName, lastName);
		setStudentId(studentId);
		setStudyYear(studyYear);
		setNumCourses(numCourses);
		setScholarship(scholarship);
		setDomestic(isDomestic);
	}

	/**
	 * @return the student’s unique identifier
	 */
	private int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the student’s unique identifier
	 */
	private void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the student’s year of study
	 */
	private int getStudyYear() {
		return studyYear;
	}

	/**
	 * @param studyYear the student’s year of study
	 * @throws HRException handles illegal values for year of study
	 */
	private void setStudyYear(int studyYear) throws HRException {
		if (studyYear < 0 || studyYear > 10)
			throw new HRException(
					"Year of study should be greater than 0 but less than or equal to 10.");
		else
			this.studyYear = studyYear;
	}

	/**
	 * @return the number of courses a student is taking
	 */
	private int getNumCourses() {
		return numCourses;
	}

	/**
	 * @param numCourses the number of courses a student is taking
	 * @throws HRException to handle an illegal amount of courses being taken
	 */
	private void setNumCourses(int numCourses) throws HRException {
		if (numCourses < 0 || numCourses >= 6)
			throw new HRException(
					"Courses should be greater than 0 but less than 6.");
		else
			this.numCourses = numCourses;
	}

	/**
	 * @return the amount of a student’s scholarship
	 */
	protected double getScholarship() {
		return scholarship;
	}

	/**
	 * @param scholarship the amount of a student’s scholarship
	 * @throws HRException to handle an illegal scholarship amount
	 */
	private void setScholarship(double scholarship) throws HRException {
		if (scholarship < 60000 || scholarship > 120000)
			throw new HRException(
					"Scholarship should be greater than $60,000 but no more than $120,000.");
		else
			this.scholarship = scholarship;
	}

	/**
	 * @return {@code true} if student is a domestic student and {@code false}
	 *         otherwise
	 */
	protected boolean isDomestic() {
		return isDomestic;
	}

	/**
	 * @param isDomestic {@code true} if student is a domestic student and
	 *                   {@code false} otherwise
	 */
	private void setDomestic(boolean isDomestic) {
		this.isDomestic = isDomestic;
	}

	/**
	 * @return Yearly tuition of the student
	 */
	public abstract double calculateTuition();

	/**
	 * @return formatted string to display results, adding on to the person
	 *         classes' toString
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"Occupation: %s Student\nStudent ID: %-15d\tYear of Study: %-9d\tNumber of Courses: %-5d\tScholarship ($): %-20.2f\tDomestic Student: %s\n",
				this instanceof Graduate ? "Graduate" : "Undergraduate",
				studentId, studyYear, numCourses, scholarship,
				isDomestic ? "Yes" : "No");
	}
}
