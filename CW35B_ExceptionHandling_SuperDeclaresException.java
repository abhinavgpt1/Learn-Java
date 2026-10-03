import java.io.FileNotFoundException;
import java.io.IOException;

class SuperClass {
	void method() throws IOException {
		System.out.println("SuperClass method");
	}

	void method2() throws IOException {
		System.out.println("SuperClass method2");
	}

	void method3() throws IOException {
		System.out.println("SuperClass method3");
	}

	void method4() throws IOException {
		System.out.println("SuperClass method4");
	}
}

class SubClass extends SuperClass {
	// void method() throws SQLException { // error: method() in SubClass cannot override method() in SuperClass
	// void method() throws Exception { // error: method() in SubClass cannot override method() in SuperClass
	void method() throws FileNotFoundException { // child exception works
		System.out.println("SubClass method");
	}

	void method2() throws IOException { // same exception works
		System.out.println("SubClass method2");
		throw new IOException("IOException from SubClass method2");
	}

	void method3() {
		System.out.println("SubClass method3");
	}

	void method4() throws NullPointerException {
		System.out.println("SubClass method4 with Unchecked exception");
		throw new NullPointerException("property not found, for example");
	}
}

public class CW35B_ExceptionHandling_SuperDeclaresException {
	public static void main(String args[]) {
		// Exception handling in method overriding - Case 2 off 2
		// SuperClass method declares an exception and variations of Subclass exception declaration
		// Key rule illustrated:
		// - If a superclass method declares checked exceptions, overriding methods in subclasses can declare the same checked exceptions or their subtypes, or no exception at all. They cannot declare new checked exceptions that are not declared in the superclass method.
		// - Overriding methods may declare and throw unchecked/runtime exceptions (subtypes of RuntimeException or Error) even if the superclass method declares checked exceptions.
		
		SuperClass refBase = new SubClass();
		try {
			refBase.method();
			refBase.method2();
			// needs handling as this call is tied to SubClass at compile-time which throws checked exception
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Needs handling as refBase.method() and method2() are tied to \"throws\" with a checked exception");
		}

		System.out.println();

		// [IMP] Rule: Java checks exceptions based on the reference type at compile time.
		SubClass refDer = new SubClass();
		refDer.method3(); // no need of handling exception since method3() in SubClass does not declare any checked exception
		try {
			refBase.method3(); // needs handling as this call is tied to SuperClass at compile-time which
								// throws checked exception. At runtime, it calls SubClass.method3() which does
								// not throw any checked exception, but the compiler does not know that.
		} catch (IOException ex) {
			System.out.println("Doesn't come here since SubClass.method3() does not throw any checked exception as per current impl.");
		}
		
		System.out.println();

		refDer.method4(); // no need of handling checked exception, though can handle NullPointerException, but not required since it is an unchecked exception

		// Tip: run from terminal for correct results
	}
}

/**
 * Output:
 * -------
 * SubClass method
 * SubClass method2
 * IOException from SubClass method2
 * Needs handling as refBase.method() and method2() are tied to "throws" with a checked exception
 * 
 * SubClass method3
 * SubClass method3
 * 
 * SubClass method4 with Unchecked exception
 * Exception in thread "main" java.lang.NullPointerException: property not found, for example
 *         at SubClass.method4(CW35B_ExceptionHandling_SuperDeclaresException.java:40)
 *         at CW35B_ExceptionHandling_SuperDeclaresException.main(CW35B_ExceptionHandling_SuperDeclaresException.java:74)
 */
