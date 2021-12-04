package a3;

/**
 * Extends staff class to model a support staff member of Springfield
 * University.
 * 
 * @author Jade Wei <br>
 *         Date: July 28, 2021 <br>
 *         Student Number: 20224398
 * 
 */
public class SupportStaff extends Staff {
	private int weeklyHours;
	private double basePay;

	/**
	 * Creates new staff with all values other than weeklyHours and basePay
	 * passed to the parent staff class to be initialized.
	 * 
	 * @param age         passed to parent
	 * @param firstName   passed to parent
	 * @param lastName    passed to parent
	 * @param staffId     passed to parent
	 * @param yearsWorked passed to parent
	 * @param department  passed to parent
	 * @param weeklyHours the number of hours the employee works per week
	 * @param basePay     the rate of pay per hour for the employee
	 * @throws HRException to handle illegal values
	 */
	SupportStaff(int age, String firstName, String lastName, int staffId,
			int yearsWorked, String department, int weeklyHours, double basePay)
			throws HRException {
		super(age, firstName, lastName, staffId, yearsWorked, department);
		setWeeklyHours(weeklyHours);
		setBasePay(basePay);
	}

	/**
	 * @return the number of hours the employee works per week
	 */
	private int getWeeklyHours() {
		return weeklyHours;
	}

	/**
	 * @param weeklyHours the number of hours the employee works per week
	 */
	private void setWeeklyHours(int weeklyHours) {
		this.weeklyHours = weeklyHours;
	}

	/**
	 * @return the rate of pay per hour for the employee
	 */
	private double getBasePay() {
		return basePay;
	}

	/**
	 * @param basePay the rate of pay per hour for the employee
	 * @throws HRException to handle invalid wage rate
	 */
	private void setBasePay(double basePay) throws HRException {
		if (basePay < 15)
			throw new HRException(
					"The base pay should be greater or equal to 15.");
		else
			this.basePay = basePay;
	}

	/**
	 * @return yearly salary of a support staff member
	 */
	@Override
	protected double calculatePay() {
		return (this.getBasePay() * this.getWeeklyHours()) * 52;
	}

	/**
	 * @return formatted string adding onto parents' toString methods to display
	 *         results
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"\nHours Worked per Week: %d\tBase Pay ($): %-15.2f\tAnnual Salary: %.2f$",
				weeklyHours, basePay, calculatePay());
	}

}
