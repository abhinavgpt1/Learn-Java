import java.util.Scanner;

public class hw8 {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Input string");
		String s = cin.nextLine();
		System.out.println("Input substring");
		String sub = cin.nextLine();
		int cnt = 0, l = 0, i;

		for (i = 0; i < s.length(); i = i + l) {
			// System.out.println("works");

			l = s.indexOf(sub, l + 1);
			System.out.println(l);
			if (l == -1)
				break;

			cnt++;
			// System.out.println(cnt);

		}
		System.out.println(cnt);
	}
}
