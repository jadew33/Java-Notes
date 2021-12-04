package quizzes;

class Test

{

	public static void main(String[] args) {
		int[] points = new int[500];
		points[1] = 3;
		points[2] = 7;
		points[3] = 13;

		for (int i = 4; i <= 400; i++) {
			points[i] = (3 * points[i - 1]) - (3 * points[i - 2])
					+ points[i - 3];
		}

		for (int i = 1; i < 400; i++) {
			System.out.println(points[i]);
		}
	}

}
