import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CW33_ThrowsKeyword {
	public static void main(String[] args) throws IOException {
		doInputs();
		// Since doInputs() throws a checked exception, we need to handle it or declare it in the method signature
		// So, we declare it in the method signature of main() using throws keyword.
		// We can throw the same exception or a parent class of it.
	}

	static void doInputs() throws IOException {
		InputStreamReader rdr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rdr);
		System.out.print("Enter String: ");
		String s = br.readLine();
		System.out.print("Enter integer: ");
		int i = Integer.parseInt(br.readLine());
		System.out.println("Output: " + s + " " + i);
	}
}
/**
 * Output:
 * Enter String: Hello
 * Enter integer: 10
 * Output: Hello 10
 */
