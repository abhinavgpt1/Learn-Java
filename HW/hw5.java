import java.util.Scanner;

public class hw5 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int[] a = getArray(3, cin);
		int[] b = getArray(3, cin);
		int[] add = add(a, b, 3);

		System.out.println("Addition of Arrays is:");

		show(add);
	}

	static int[] getArray(int n, Scanner cin) {
		int i;
		int[] pitaji = new int[n];
		for (i = 0; i < n; i++) {
			System.out.println("Enter Value:");
			pitaji[i] = cin.nextInt();
		}
		return pitaji;
	}

	static int[] add(int[] r1, int[] r2, int n) {
		int i;
		int[] pitaji = new int[n];
		for (i = 0; i < n; i++) {
			pitaji[i] = r1[i] + r2[i];
		}
		return pitaji;
	}

	static void show(int[] ref) {
		int i;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
		}
	}
}
