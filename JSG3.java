class JSG3 {
	public static void main(String obj[]) {
		int a = 2, b = 7;
		int x, y, z;
		x = 2;
		y = 3;
		z = x + y;
		System.out.println(z);
		float c = 2.0f;
		System.out.println(c + 3);
		float f = (float) (3.14 * 10 * 10);
		System.out.println(f);
		// float q=1.9; erroneous since, implict narrowing is not possible (double -> float)

		long l1 = 89L;
		long l2 = 89l;
		long l3 = 89; // Widening. 89 is by default int.
		if (l1 == l2 && l2 == l3) {
			System.out.println("All long representations are equal");
			System.out.println("Representation with L is recommended since 89l looks like 891");
		}
		// Narrowing / Widening -> Casting between primitive data types
		// != 
		// Autoboxing (Boxing) / Unboxing -> Wrapper classes
	}
}

/**
 * Output:
 * -------
 * 5
 * 5.0
 * 314.0
 * All long representations are equal
 * Representation with L is recommended since 89l looks like 891
 */