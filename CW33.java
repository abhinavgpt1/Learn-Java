// intro to throws
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CW33 {
	public static void main(String[] args) throws IOException {
		// throw the unchecked exception or a parent class of it
		doInputs();
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
