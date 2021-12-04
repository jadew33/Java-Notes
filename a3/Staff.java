package a3;

/**
 * Extends person class to model a staff member of Springfield University.
 * Accessors and mutators for fields that are not used in subclasses are left
 * private, which could be changed to protected if needed.
 * 
 * @author Jade Wei <br>
 *         Date: July 28, 2021 <br>
 *         Student Number: 20224398
 * 
 */
public abstract class Staff extends Person {

	private int staffId, yearsWorked;
	Department department;

	enum Department {
		ARTS_SCI, ENGINEERING, EDUCATION, HEALTH_SCI;
	}

	/**
	 * Creates new staff with age, firstName, and lastName passed to the parent
	 * class to be initialized.
	 * 
	 * @param age         passed to parent
	 * @param firstName   passed to parent
	 * @param lastName    passed to parent
	 * @param staffId     the staff member’s unique identifier
	 * @param yearsWorked the number of years that employee has worked at the
	 *                    university
	 * @param department  the department in which the staff member works
	 * @throws HRException to handle illegal values
	 */
	Staff(int age, String firstName, String lastName, int staffId,
			int yearsWorked, String department) throws HRException {
		super(age, firstName, lastName);
		setStaffId(staffId);
		setYearsWorked(yearsWorked);
		setDepartment(department);
	}

	/**
	 * @return the staff member’s unique identifier
	 */
	private int getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staff member’s unique identifier
	 */
	private void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the number of years that employee has worked at the university
	 */
	protected int getYearsWorked() {
		return yearsWorked;
	}

	/**
	 * @param yearsWorked the number of years that employee has worked at the
	 *                    university
	 * @throws HRException to handle an invalid number of years worked
	 */
	private void setYearsWorked(int yearsWorked) throws HRException {
		if (yearsWorked < 0 || yearsWorked > 40)
			throw new HRException(
					"Years worked must be greater than 0 and less than 40.");
		else
			this.yearsWorked = yearsWorked;
	}

	/**
	 * @return the department in which the staff member works
	 */
	private Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department in which the staff member works
	 * @throws HRException to handle an invalid department
	 */
	private void setDepartment(String department) throws HRException {
		switch (department.toLowerCase()) {
		case "arts and sciences":
			this.department = Department.ARTS_SCI;
			break;
		case "engineering":
			this.department = Department.ENGINEERING;
			break;
		case "education":
			this.department = Department.EDUCATION;
			break;
		case "health sciences":
			this.department = Department.HEALTH_SCI;
			break;
		default:
			throw new HRException("Invalid department!");
		}
	}

	/**
	 * @return formatted string to display results, adding on to the person
	 *         classes' toString
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"Occupation: %s\nStaff ID: %-15d\tYears Worked: %-10d\tDepartment: %s\t",
				this instanceof FacultyMember ? "Faculty Member"
						: "Support Staff",
				staffId, yearsWorked, department.toString());
	}

	/**
	 * @return Yearly wage of staff member
	 */
	protected abstract double calculatePay();
}
