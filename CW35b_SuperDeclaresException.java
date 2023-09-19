// // Exception handling in method overriding - Case 2 off 2
// SuperClass method declares an exception and variations of Subclass exception declaration

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

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
	// void method() throws SQLException{ // error: method() in SubClass cannot override method() in SuperClass
	// void method() throws Exception{ // error: method() in SubClass cannot override method() in SuperClass
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

public class CW35b_SuperDeclaresException {
	public static void main(String args[]) {
		SuperClass refBase = new SubClass();
		try {
			refBase.method();
			refBase.method2();
			// needs handling as this call is tied to SubClass at compile-time which throws checked exception
		} catch (IOException ex) {
			System.out.println("Needs handling as refBase.method() and method2() are tied to \"throws\"");
		}

		SubClass refDer = new SubClass();
		refDer.method3(); // no need of handling checked exception as method3 in SubClass doesn't come in picture
		try {
			refBase.method3();
		} catch (IOException ex) {
			System.out.println("Needs handling as refBase.method3() is tied to \"throws\"");
		}
		refDer.method4(); // no need of handling checked exception, though can handle NullPointerException
	}
	/**
	 * Output:
	 * -------
	 * SubClass method
	 * SubClass method2
	 * Needs handling as refBase.method() and method2() are tied to "throws"
	 * SubClass method3
	 * SubClass method3
	 * SubClass method4 with Unchecked exception
	 * Exception in thread "main" java.lang.NullPointerException: property not found, for example
	 *         at SubClass.method4(CW35b_SuperDeclaresException.java:44)
	 *         at CW35b_SuperDeclaresException.main(CW35b_SuperDeclaresException.java:66)
	 */
}
