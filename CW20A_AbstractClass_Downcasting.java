abstract class Shape {
	abstract void area();

	void line() {
		System.out.println("-----------");
	}
};

class Rect extends Shape {
	void area() {
		System.out.println("Area of Rect");
	}

	void dline() {
		System.out.println("$$$");
	}
};

class CW20A_AbstractClass_Downcasting {
	public static void main(String[] args) {
		Rect robj = new Rect();
		robj.area();
		robj.line();
		robj.dline();

		Shape ref = new Rect(); // upcasting (def in CW13B)
		ref.area();
		ref.line();
		((Rect) ref).dline(); // downcasting
		System.out.println("Hello World!");

		// FYI: An abstract class can have a constructor in Java, which is automatically
		// invoked when a concrete subclass is instantiated.
		// While an abstract class cannot be directly instantiated, its constructor
		// serves to initialize fields of its class & perform setup tasks for its subclasses.

		/**
		 * Definitions:
		 * Abstract Class: A class declared with the abstract keyword that cannot be
		 * instantiated and may contain both abstract and concrete methods; it serves as
		 * a base class for inheritance and a partial blueprint for its subclasses.
		 * 
		 * Downcasting: The process of converting a superclass reference to a subclass
		 * reference, allowing access to subclass-specific methods and fields, but
		 * requires explicit casting and can lead to ClassCastException if the
		 * referenced object is not an instance of the target subclass.
		 * 
		 */
	}
}

/**
 * Output:
 * Area of Rect
 * -----------
 * $$$
 * Area of Rect
 * -----------
 * $$$
 * Hello World!
 */