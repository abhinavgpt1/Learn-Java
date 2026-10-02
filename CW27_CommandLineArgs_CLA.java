class CW27_CommandLineArgs_CLA {
	public static void main(final Integer[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			// sum = sum + Integer.parseInt(ref[i]); // ref[i] is Integer, so parsing throws error
			sum = sum + Integer.valueOf(ref[i]); //valueOf accepts int and string both

		}
		System.out.println("sum (cla - custom int array) = " + sum);
		// ref = new String[10]; // final ref, so not possible to change
	}

	public static void main(final String[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			sum = sum + Integer.parseInt(ref[i]);
		}
		System.out.println("sum (cla) = " + sum);
		// ref = new String[10]; // final ref, so not possible to change
		Integer ary[] = new Integer[3];
		ary[0] = Integer.valueOf(5);
		ary[1] = Integer.valueOf(10);
		ary[2] = Integer.valueOf(20);
		main(ary);

		// The above main method is not executed on cmd>java CW27_CommandLineArgs_CLA
		// because the main method with String[] is the entry point of the program.
	}

}

class CLA2 {
	public static void main(final String[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			sum = sum + Integer.parseInt(ref[i]);
		}
		System.out.println("sum (cla2) = " + sum);
		// ref = new String[0]; //final ref, so not possible
	}
}

/**
 * cmd> javac CW27_CommandLineArgs_CLA.java
 * cmd> java CW27_CommandLineArgs_CLA
 * 
 * Input: 1 2 3
 * Output (CW27_CommandLineArgs_CLA):
 * -------------
 * 3
 * 1
 * 2
 * 3
 * sum (cla) = 6
 * 3
 * 5
 * 10
 * 20
 * sum (cla - custom int array) = 35
 *-------------------------------------------
 * cmd> javac CW27_CommandLineArgs_CLA.java
 * cmd> java CLA2
 * 
 * Input: 1 2 3
 * Output (CLA2):
 * --------------
 * 3
 * 1
 * 2
 * 3
 * sum (cla2) = 6
 */