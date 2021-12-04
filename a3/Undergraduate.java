package a3;

/**
 * Extends student class to model an undergraduate student of Springfield
 * University
 * 
 * @author Jade Wei <br>
 *         Date: July 28, 2021 <br>
 *         Student Number: 20224398
 */
public class Undergraduate extends Student {

	private Major major;
	private boolean inResidence;

	enum Major {
		ENGLISH, COMPUTER_SCIENCE, MATHEMATICS, MEDICINE, ENGINEERING,
		EDUCATION;
	}

	/**
	 * Creates new student with all values other than major and inResidence
	 * passed to the parent student class to be initialized.
	 * 
	 * @param age         passed to parent
	 * @param firstName   passed to parent
	 * @param lastName    passed to parent
	 * @param studentId   passed to parent
	 * @param studyYear   passed to parent
	 * @param numCourses  passed to parent
	 * @param scholarship passed to parent
	 * @param isDomestic  passed to parent
	 * @param major       the student's major
	 * @param inResidence {@code true} if the Undergraduate is staying in
	 *                    residence, {@code false} otherwise
	 * @throws HRException to handle any illegal values
	 */
	Undergraduate(int age, String firstName, String lastName, int studentId,
			int studyYear, int numCourses, double scholarship,
			boolean isDomestic, String major, boolean inResidence)
			throws HRException {
		super(age, firstName, lastName, studentId, studyYear, numCourses,
				scholarship, isDomestic);
		setMajor(major);
		setInResidence(inResidence);
	}

	/**
	 * @return the student's major
	 */
	private Major getMajor() {
		return major;
	}

	/**
	 * @param major the student's major
	 * @throws HRException to handle case of invalid major
	 */
	private void setMajor(String major) throws HRException {
		switch (major.toLowerCase()) {
		case "english":
			this.major = Major.ENGLISH;
			break;
		case "computer science":
			this.major = Major.COMPUTER_SCIENCE;
			break;
		case "mathematics":
			this.major = Major.MATHEMATICS;
			break;
		case "medicine":
			this.major = Major.MEDICINE;
			break;
		case "engineering":
			this.major = Major.ENGINEERING;
			break;
		case "education":
			this.major = Major.EDUCATION;
			break;
		default:
			throw new HRException("Invalid major!");
		}

	}

	/**
	 * @return {@code true} if the Undergraduate is staying in residence,
	 *         {@code false} otherwise
	 */
	private boolean isInResidence() {
		return inResidence;
	}

	/**
	 * @param inResidence {@code true} if the Undergraduate is staying in
	 *                    residence, {@code false} otherwise
	 */
	private void setInResidence(boolean inResidence) {
		this.inResidence = inResidence;
	}

	/**
	 * @return formatted string of results, adding onto the toString of its
	 *         parents
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"Major: %-17s\tResidence: %-20s\tYearly Tuition: %.2f$",
				major.toString(), inResidence ? "Yes" : "No",
				calculateTuition());
	}

	/**
	 * @return yearly tuition of an undergraduate student, depending on whether
	 *         or not they are a domestic student and their major
	 */
	@Override
	public double calculateTuition() {
		switch (this.major) {
		case ENGLISH:
			return this.isDomestic() ? 9934 : 30263;
		case COMPUTER_SCIENCE:
			return this.isDomestic() ? 9619 : 33875;
		case MATHEMATICS:
			return this.isDomestic() ? 9567 : 34537;
		case ENGINEERING:
			return this.isDomestic() ? 12626 : 29873;
		default:
			return this.isDomestic() ? 7037 : 32416;
		}
	}
}
