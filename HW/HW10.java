import java.util.Scanner;

public class hw10 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Input string");
		String s = cin.nextLine();
		cin.close();

		int i = s.indexOf('.');
		String ss = s.substring(i + 1);
		if (ss.equals("jpg")) 
			System.out.println("Works");
		else
			System.out.println("Doesn't");
	}
}
