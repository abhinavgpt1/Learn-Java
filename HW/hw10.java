// Check the extension of the file input is .jpg
import java.util.Scanner;
public class hw10 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Input file name: ");
		String s = cin.nextLine();
		cin.close();

		int i = s.indexOf('.');
		String extension = s.substring(i + 1);
		if (extension.equalsIgnoreCase("jpg"))
			System.out.println("Is .jpg");
		else
			System.out.println("Isn't .jpg");
	}
}
