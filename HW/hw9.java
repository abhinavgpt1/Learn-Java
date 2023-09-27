// Count number of consonants, assuming input string has all english letters
import java.util.Scanner;
public class hw9 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Input string: ");
		String s = cin.nextLine();
		cin.close();
		int cntVowels = 0, cntLetters = 0;

		for (int i = 0; i < s.length(); i++) {

			char ch = s.charAt(i);
			if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
				cntLetters++;
			switch (ch) {
				case 'a':case 'A':
				case 'e':case 'E':
				case 'i':case 'I':
				case 'u':case 'O':
				case 'o':case 'U':
					cntVowels++;
			}
		}
		System.out.println(cntLetters - cntVowels);
	}
	/**
	 * Output:
	 * -------TC1--------
	 * Input string: aeiouAEIUOdfghjk
	 * -> 6
	 * -------TC2--------
	 * Input string: qwertYUIVBNM
	 * -> 9
	 * -------TC3--------
	 * Input string: qwertyasdfghjkliop
	 * 14
	 */
}
