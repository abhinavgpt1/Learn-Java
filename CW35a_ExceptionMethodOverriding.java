// Exception handling in method overriding - Case 1 of 2
// SuperClass method doens't declare exception and variations of Subclass exception declaration

// Key rule illustrated:
// - If a superclass method declares no checked exceptions, overriding methods in subclasses cannot declare new checked exceptions.
// - Overriding methods may declare and throw unchecked/runtime exceptions (subtypes of RuntimeException or Error) even if the superclass method does not.

class SuperClass {
	void method() {
		System.out.println("SuperClass method");
	}

	void method2() {
		System.out.println("SuperClass method2");
	}
}

class SubClass extends SuperClass {
	// The following method would not compile because FileNotFoundException is a checked exception:
	// @Override
	// void method() throws FileNotFoundException {
	//     System.out.println("SubClass method");
	// }
	// ERROR: Exception FileNotFoundException is not compatible with throws clause in SuperClass.method()

	@Override
	void method() {
		System.out.println("SubClass method");
	}

	// Declaring unchecked exception is allowed in override (Throwable is not checked at compile time).
	@Override
	void method2() throws NullPointerException {
		System.out.println("SubClass method2");
		// Throwing a different runtime exception is also allowed at runtime.
		throw new ArrayIndexOutOfBoundsException("some runtime exception apart from declared");
	}
}

public class CW35a_ExceptionMethodOverriding {
	public static void main(String args[]) {
		SuperClass refBase = new SubClass();
		refBase.method(); // Calls SubClass.method(), no checked exception in signature
		refBase.method2(); // method2 may throw runtime exception even though SuperClass.method2() declares none. At compile time, no checked-exception handling is required.
	}
}

/**
 * Output:
 * -------
 * SubClass method
 * SubClass method2
 * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: some runtime exception apart from declared
 * 	at SubClass.method2(CW35a_ExceptionMethodOverriding.java:34)
 * 	at CW35a_ExceptionMethodOverriding.main(CW35a_ExceptionMethodOverriding.java:42)
 */