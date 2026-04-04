class JSG2 {
	public static void main(String obj[]) {
		System.out.print("Hello World!");
		System.out.println("Hello Again");
		System.out.println("Bye World!");
		int x = 2, y = 3, z;
		z = x + y;
		System.out.println("Sum" + z);
		System.out.println(z + "is Sum");
		System.out.println("Sum" + x + y);
		System.out.println("Sum" + (x + y));
		System.out.println("Prod" + x * y);
		System.out.println(x + "" + y);
		System.out.println("" + x + y);
		System.out.println(x + y + "");

		System.out.println();

		// Block comment is ignored in sout
		System.out.println(/*this will be ignored*/"Hello World");
	}
}

/**
 * Output:
 * -------
 * Hello World!Hello Again
 * Bye World!
 * Sum5
 * 5is Sum
 * Sum23
 * Sum5
 * Prod6
 * 23
 * 23
 * 5
 * 
 * Hello World
 */
