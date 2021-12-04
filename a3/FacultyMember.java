package a3;

/**
 * Extends staff class to model a faculty member of Springfield University.
 * 
 * @author Jade Wei <br>
 *         Date: July 28, 2021 <br>
 *         Student Number: 20224398
 * 
 */
public class FacultyMember extends Staff {
	private DegreeType degreeType;

	enum DegreeType {
		BACHELOR, GRADUATE;
	}

	/**
	 * Creates new staff with all values other than degreeType passed to the
	 * parent staff class to be initialized.
	 * 
	 * @param age         passed to parent
	 * @param firstName   passed to parent
	 * @param lastName    passed to parent
	 * @param staffId     passed to parent
	 * @param yearsWorked passed to parent
	 * @param department  passed to parent
	 * @param degreeType  the highest level of training of a FacultyMember
	 * @throws HRException to handle illegal values
	 */
	FacultyMember(int age, String firstName, String lastName, int staffId,
			int yearsWorked, String department, String degreeType)
			throws HRException {
		super(age, firstName, lastName, staffId, yearsWorked, department);
		setDegreeType(degreeType);
	}

	/**
	 * @return the highest level of training of a FacultyMember
	 */
	private DegreeType getDegreeType() {
		return degreeType;
	}

	/**
	 * @param degreeType the highest level of training of a FacultyMember
	 * @throws HRException to handle invalid degree type
	 */
	private void setDegreeType(String degreeType) throws HRException {
		switch (degreeType.toLowerCase()) {
		case "bachelor":
			this.degreeType = DegreeType.BACHELOR;
			break;
		case "graduate":
			this.degreeType = DegreeType.GRADUATE;
			break;
		default:
			throw new HRException("Invalid degree type!");
		}
	}

	/**
	 * @return formatted string adding onto parents' toString() to display
	 *         results
	 */
	@Override
	public String toString() {
		return super.toString()
				+ String.format("\nDegree Type: %-15s\tAnnual Salary: %.2f$",
						degreeType.toString(), calculatePay());
	}

	/**
	 * @return yearly salary of the faculty member, dependent on the degree they
	 *         have and the number of years worked
	 */
	@Override
	protected double calculatePay() {
		int[][] salary = {
				{ 53345, 56345, 59345, 63345, 66345, 69345, 73345, 76345,
						79345 },
				{ 58567, 62567, 66567, 70567, 74567, 78567, 82567, 87567,
						91567 } };
		int yearsWorked = this.getYearsWorked() > 8 ? 8 : this.getYearsWorked(),
				salaryType = this.getDegreeType() == DegreeType.BACHELOR ? 0
						: 1;
		return salary[salaryType][yearsWorked];
	}
}
