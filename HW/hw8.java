// find index and count of all substring in a string
import java.util.Scanner;

public class hw8 {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Input string: ");
		String s = cin.nextLine();
		System.out.print("Input substring: ");
		String sub = cin.nextLine();
		int cnt = 0, l = -1, i=0;

		// works when substring has all characters same
		for (;;) {
			l = s.indexOf(sub, l + 1);
			if (l == -1)
				break;
			System.out.println(l);
			cnt++;
		}
		System.out.println("Occurences:" + cnt);
	}
	/**
	 * Output:
	 * ----------TC1-------------------
	 * Input string: aaaaaaaa
	 * Input substring: aa
	 * 0
	 * 1
	 * 2
	 * 3
	 * 4
	 * 5
	 * 6
	 * Occurences:7
	 * ----------TC2-------------------
	 * 0
	 * 5
	 * 10
	 * 19
	 * Occurences:4
	 */
}
