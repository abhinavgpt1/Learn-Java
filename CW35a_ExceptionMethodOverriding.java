import java.io.FileNotFoundException;

// Exception handling in method overriding - Case 1 off 2
// SuperClass method doens't declare exception and variations of Subclass exception declaration
class SuperClass {
	void method() {
		System.out.println("SuperClass method");
	}

	void method2() {
		System.out.println("SuperClass method2");
	}
}

class SubClass extends SuperClass {
	// @Override
	// void method() throws FileNotFoundException {
	// // error: Exception FileNotFoundException is not compatible with throws clause in SuperClass.method()

	// no declaration of exception => checked exception
	
	@Override
	void method() {
		System.out.println("SubClass method");
	}

	@Override // certifies overriding
	void method2() throws NullPointerException {
		System.out.println("SubClass method2");
		throw new ArrayIndexOutOfBoundsException("some runtime exception apart from declared");
	}
}

public class CW35a_ExceptionMethodOverriding {
	public static void main(String args[]) {
		SuperClass refBase = new SubClass();
		refBase.method(); // no declaration of exception => no exception handling (for checked exception)
		refBase.method2(); // no exception handling required for checked exception, can for Runtime
							// exceptions (not recommended)
	}
	/**
	 * Output:
	 * -------
	 * SubClass method
	 * SubClass method2
	 * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: some
	 * runtime exception apart from declared
	 * at SubClass.method2(CW35a_ExceptionMethodOverriding.java:20)
	 * at
	 * CW35a_ExceptionMethodOverriding.main(CW35a_ExceptionMethodOverriding.java:28)
	 */
}
