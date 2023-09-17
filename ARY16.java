import java.util.Scanner;

class ARY16 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int n, i;
		System.out.println("Enter N:");
		n = cin.nextInt();
		int[] A = new int[n];
		for (i = 0; i < n; i++) {
			System.out.println("Enter Value:");
			A[i] = cin.nextInt();
		}
		show(A);
		show(A);
		int[] chmn = getArray(3, cin);
		show(chmn);
	}

	static void show(int[] ref) {
		int i;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			ref[i] = ref[i] + 10;
		}
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
}
