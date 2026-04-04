class B {
	B() {
		System.out.println("Base Class");
	}

	B(int x) {
		System.out.println(x);
	}
};

class D extends B {
	D() {
		System.out.println("Derived Class");
	}

	D(float f, int i) {
		// super();
		super(i);
		System.out.println(f);

		// Rule: super() if mentioned needs to be the first line of method, else compilation error.
		// eg. sout(f); super(); [WRONG]

		// PTR: every class in java extends Object class, so we're calling super() of
		// Object class everytime constructor of any class executes.
		// eg. D calls super() for A, A calls super for Object => there is multilevel inheritance

		// PTR: this() will call the default constructor of current class
	}
};

public class CW18_Constructors {
	public static void main(String[] args) {
		D obj = new D();
		D obj1 = new D(2.6f, 6);
		System.out.println("Hello World!");
	}
}

/**
 * Output:
 * -------
 * Base Class
 * Derived Class
 * 6
 * 2.6
 * Hello World!
 */