import java.util.Scanner;

//Check the consonants(but not general case)
public class hw9 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Input string");
		String s = cin.nextLine();
		int cntVowels = 0, cntLetters = 0;

		for (int i = 0; i < s.length(); i++) {

			char ch = s.charAt(i);
			if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
				cntLetters++;
			switch (ch) {
				case 'a':
				case 'e':
				case 'i':
				case 'u':
				case 'o':
					cntVowels++;
			}
		}
		System.out.println(cntLetters - cntVowels);
	}
}
