package a1;

/**
 * Short methods covering fundamental Java concepts.
 *
 */
public class A1 {
	/**
     * Returns the maximum (most positive) value that an {@code int} can
     * represent.
     * 
     * @return the maximum (most positive) value that an int can represent
     */
    public static int maxInt() {
        return Integer.MAX_VALUE;
    }
	
	
    /**
     * Returns the minimum (most negative) value that an {@code int} can
     * represent.
     * 
     * @return the minimum (most negative) value that an int can represent
     */
    public static int minInt() {
        return Integer.MIN_VALUE;
    }

    /**
     * Returns the maximum (most positive) value less than 
     * {@code Double.POSITIVE_INIFINITY} that a {@code double} can
     * represent.
     * 
     * @return the maximum (most positive) value that a double can represent
     */
    public static double maxDouble() {
        return Double.MAX_VALUE;
    }

    /**
     * Removes the last two digits of a positive integer base 10 number that is
     * greater than {@code 99}.
     * 
     * @param n
     *            a positive integer number greater than 99
     * @return the integer produced by removing the last two digits of n
     */
    public static int removeLastTwoDigits(int n) {
        return (int) Math.floor(n / 100);
    }

    /**
     * Returns the last two digits of a positive integer base 10 number that is
     * greater than {@code 99}. If the last two digits start with a
     * {@code 0} then only the last digit is returned.
     * 
     * @param n
     *            a positive integer number greater than 99
     * @return the last two digits of n
     */
    public static int lastTwoDigits(int n) {
        return n % 100;
    }

    /**
     * Computes the age (in years) of a person using the following convoluted
     * algorithm:
     * 
     * <ul>
     * <li>start with the person's street {@code address}
     * <li>double the result from the previous step
     * <li>add 42 to the result from the previous step
     * <li>multiply the previous step by 50
     * <li>subtract the person's {@code birthYear} from the previous step
     * <li>subtract 50 from the previous step
     * <li>add the number of {@code birthdays} the person has had this year
     * to the previous step
     * <li>subtract 30 from the previous step
     * <li>removes all but the last two digits of the result from the previous step;
     * the last two digits are equal to the age of the person
     * </ul>
     * 
     * <p>
     * This algorithm works only for people aged 1-99
     * and for addresses less than approximately .
     * 
     * @param address
     *            the person's street address number
     * @param birthYear
     *            the person's birth year
     * @param birthdays
     *            the number of birthdays the person has had this year (either 0
     *            or 1)
     * @return the age of the person
     */
    public static int yourAge(int address, int birthYear, int birthdays) {
    	int result = address;
    	// -make sure that you follow the rest of the steps described 
    	//  in the doc comment above to compute the final result
    	// -each step should be one additional line of code (so don't
    	//  merge multiple steps together)
    	// -this means that the next line of code should be something like:
    	result *= 2;
    	result += 42;
    	result *= 50;
    	result -= birthYear;
    	result -= 50;
    	result += birthdays;
    	result -= 30;
        return lastTwoDigits(result);
    }

    /**
     * Compute the average of three values.
     * 
     * @param a
     *            a value
     * @param b
     *            a second value
     * @param c
     *            a third value
     * @return the average of the three values
     */
    public static double avg(int a, int b, int c) {
        return (a + b + c) / 3.0;
    }

    /**
     * Returns the wind chill for air temperatures equal to or below 0 degrees
     * Celcius and wind velocities equal to or greater than 5 km/h.
     * 
     * <p>
     * Wind chill is an index that indicates how cold the weather feels to the
     * average person when there is some wind. For example, if the air
     * temperature is -5 degrees Celcius and the wind chill is -15 then it means
     * that it feels similar to a windless day where the temperature is -15
     * degrees Celcius.
     * 
     * @param airTemp
     *            the temperature in degrees Celcius (must be 0 degrees or less)
     * @param windSpeed
     *            the wind speed in km/h (must be 5 km/h or greater)
     * @return the wind chill index
     * @see <a href="http://climate.weather.gc.ca/glossary_e.html#w">
     *      Environment and Climate Change Canada wind chill definition</a>
     */
    public static double windChill(double airTemp, double windSpeed) {
    	return 13.12 + (0.6215 * airTemp) + (0.3965 * airTemp - 11.37) * Math.pow(windSpeed, 0.16);
    }
  
    /**
     * This main method simply calls each method and prints the value
     * returned by each method.
     * 
     * <p>
     * Students are encouraged to change the argument values passed 
     * to the methods to check if their method implementations work
     * correctly.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
    	// print the result of calling maxInt
    	System.out.println("maxInt:");
    	int max = A1.maxInt();
    	System.out.println(max);
    	
    	// print the result of calling minInt
    	System.out.println();
    	System.out.println("minInt:");
    	int min = A1.minInt();
    	System.out.println(min);
    	
    	// print the result of calling maxDouble
    	System.out.println();
    	System.out.println("maxDouble:");
    	double maxDb = A1.maxDouble();
    	System.out.println(maxDb);
    	
    	// print the result of calling removeLastTwoDigits
    	System.out.println();
    	System.out.println("removeLastTwoDigits:");
    	int firstSix = A1.removeLastTwoDigits(12345678);
    	System.out.println(firstSix);
    	
    	// print the result of calling lastTwoDigits
    	System.out.println();
    	System.out.println("lastTwoDigits:");
    	int lastTwo = A1.lastTwoDigits(12345678);
    	System.out.println(lastTwo);
    	
    	// print the result of calling yourAge
    	System.out.println();
    	System.out.println("yourAge:");
    	int age = A1.yourAge(9876, 2000, 0);
    	System.out.println(age);
    	
    	// print the result of calling avg
    	System.out.println();
    	System.out.println("avg:");
    	double avg = A1.avg(1, 2, 3);
    	System.out.println(avg);
    	
    	// print the result of calling windChill
    	System.out.println();
    	System.out.println("windChill:");
    	double wc = A1.windChill(-5.0, 7.5);
    	System.out.println(wc);
    }
}
