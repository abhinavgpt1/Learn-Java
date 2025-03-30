class CLA {
	public static void main(final Integer[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			// sum=sum+Integer.parseInt(ref[i]); // ref[i] is Integer, so parsing throws error
			sum=sum+Integer.valueOf(ref[i]); //valueOf accepts int and string both

		}
		System.out.println("sum (cla - custom int array) = " + sum);
		// ref=new String[10]; // final ref, so not possible
	}

	public static void main(final String[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			sum = sum + Integer.parseInt(ref[i]);
		}
		System.out.println("sum (cla) = " + sum);
		// ref=new String[10]; // final ref, so not possible
		Integer ary[] = new Integer[3];
		ary[0] = Integer.valueOf(5);
		ary[1] = Integer.valueOf(10);
		ary[2] = Integer.valueOf(20);
		main(ary);
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
 * Input: 1 2 3
 * 
 * Output (CLA):
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
 *
 * Output (CLA2):
 * --------------
 * 3
 * 1
 * 2
 * 3
 * sum (cla2) = 6
 */