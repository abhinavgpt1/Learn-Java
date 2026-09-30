import java.util.Scanner;

class CW16_ArrayManipulation_Scanner {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int n, i;
		System.out.print("Enter N: ");
		n = cin.nextInt();
		int[] A = new int[n];
		for (i = 0; i < n; i++) {
			System.out.print("Enter Value: ");
			A[i] = cin.nextInt();
		}
		show(A);
		System.out.println("--------------");
		show(A);
		System.out.println("--------------");
		int[] chmn = getArray(3, cin);
		show(chmn);
	}

	static void show(int[] ref) {
		int i;
		for (i = 0; i < ref.length; i++) {
			System.out.print(ref[i] + " ");
			ref[i] = ref[i] + 10; // late update of array values
		}
		System.out.println();
	}

	static int[] getArray(int n, Scanner cin) {
		int i;
		int[] pitaji = new int[n];
		for (i = 0; i < n; i++) {
			System.out.print("Enter Value: ");
			pitaji[i] = cin.nextInt();
		}
		return pitaji;
	}
}

/**
 * Output:
 * cmd>javac ARY16.java
 * 
 * cmd>java ARY16      
 * Enter N:
 * 3
 * Enter Value:
 * 1
 * Enter Value:
 * 2
 * Enter Value:
 * 3
 * 1 2 3 
 * --------------
 * 11 12 13 
 * --------------
 * Enter Value:
 * 4
 * Enter Value:
 * 5
 * Enter Value:
 * 6
 * 4 5 6 
 */