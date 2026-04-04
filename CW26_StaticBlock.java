class CustomClass {
	static {
		System.out.println("Static block of CustomClass");
	}
}

public class CW26_StaticBlock {
	static {
		System.out.println("Inside Static Block");
	}

	public static void main(String[] args) {
		System.out.println("Inside Main");

		// Can skip if you don't know exceptions and jdbc.
		// static block of CustomClass doesn't execute unless instantiated. But this can be bypassed by loading just the class without instantiation.
		try {
			// Instantiation can be avoided by LOADING the class with the help of Class.forName() 
			// // helpful in JDBC (older versions where driver loading was needed)
			Class.forName("CustomClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ref: https://youtu.be/_7q4kMfJPDw?si=cP5ncaI2KTjtFKry
	}
}

/**
 * Output:
 * -------
 * Inside Static Block
 * Inside Main
 * Static block of CustomClass
 */