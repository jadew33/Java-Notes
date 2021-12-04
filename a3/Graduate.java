package a3;

/**
 * Extends student class to model a graduate student of Springfield University.
 *
 * @author Jade Wei <br>
 *         Date: August 5, 2021 <br>
 *         Student Number: 20224398
 */
public class Graduate extends Student {

	private Program program;
	private double funding;

	enum Program {
		MASTERS, PROFESSIONAL_STUDIES, PHD
	}

	/**
	 * Creates new student with all values other than program and funding passed
	 * to the parent student class to be initialized.
	 * 
	 * @param age         passed to parent
	 * @param firstName   passed to parent
	 * @param lastName    passed to parent
	 * @param studentId   passed to parent
	 * @param studyYear   passed to parent
	 * @param numCourses  passed to parent
	 * @param scholarship passed to parent
	 * @param isDomestic  passed to parent
	 * @param program     the student’s program
	 * @param funding     the amount of research funding available for the
	 *                    student
	 * @throws HRException to handle illegal values
	 */
	Graduate(int age, String firstName, String lastName, int studentId,
			int studyYear, int numCourses, double scholarship,
			boolean isDomestic, String program, double funding)
			throws HRException {
		super(age, firstName, lastName, studentId, studyYear, numCourses,
				scholarship, isDomestic);
		setProgram(program);
		setFunding(funding);
	}

	/**
	 * @return the student’s program
	 */
	private Program getProgram() {
		return program;
	}

	/**
	 * @param program the student’s program
	 */
	private void setProgram(String program) throws HRException {
		switch (program.toLowerCase()) {
		case "masters":
			this.program = Program.MASTERS;
			break;
		case "professional studies":
			this.program = Program.PROFESSIONAL_STUDIES;
			break;
		case "phd":
			this.program = Program.PHD;
			break;
		default:
			throw new HRException("Invalid program!");
		}
	}

	/**
	 * @return the amount of research funding available for the student
	 */
	private double getFunding() {
		return funding;
	}

	/**
	 * @param funding the amount of research funding available for the student
	 */
	private void setFunding(double funding) throws HRException {
		if (funding < 1000)
			throw new HRException("Funding should be greater than 1000.");
		else
			this.funding = funding;
	}

	/**
	 * @return formatted string of results, adding onto the toString of its
	 *         parents
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"Program: %-15s\tResearch Funding: %.2f$\tYearly Tuition: %.2f$",
				program.toString(), funding, calculateTuition());
	}

	/**
	 * @return the yearly tuition of a graduate student with the amount of
	 *         research funding and scholarship received subtracted
	 */
	@Override
	public double calculateTuition() {
		return (this.isDomestic() ? 5603.65 : 8426.27) - this.getFunding()
				- this.getScholarship();
	}

}
