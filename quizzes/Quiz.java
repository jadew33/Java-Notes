package quizzes;

import java.util.ArrayList;

public class Quiz {

	public static void main(String[] args)

	{
		System.out.println(4 / 5.0); // 0.8
		System.out.println(4 / 5); // 0
		System.out.println(-3 / 4); // 0
		System.out.println("a" + 1 + 2); // a12
		System.out.println(1 + 2 + "a"); // 3a
		double a = 2.75, b = 1.25, c = -1.25, d = -2.75;
		System.out.printf("%d %d %d %d", (int) a, (int) b, (int) c, (int) d);
		// 2 1 -1 -2
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(4);
		nums.add(5);
		nums.remove(0);
		nums.remove(new Integer(2));
		System.out.println(nums.toString()); // [3, 4, 5]
	}

}
