class RECT {
	void area() {
		System.out.println("Le Rect");
	}
};

class Circle extends RECT {
	void Carea() {
		System.out.println("Le Circle");
	}

	void Rarea() {
		area();
		super.area();
	}

	void area(int a, int b) {
		System.out.println("Method overloading 1: " + (a * b));
	}

	int area(int a, int b, int c) {
		System.out.println("Method overloading 2: " + (a * b * c));
		return a * b * c;
	}
};

class CW13A_Inheritance_CompiletimePolymorphism_MethodOverloading {
	public static void main(String[] args) {
		Circle obj = new Circle();
		obj.area();
		System.out.println("-------");
		obj.Rarea();

		/**
		 * Definition:
		 * Method Overloading: Happens when a subclass provides multiple methods with
		 * the same name but different parameter lists (number, type, or order of
		 * parameters), enabling compile-time polymorphism. Return type can be the same
		 * or different, but it does not contribute to method overloading.
		 * 
		 * Compile-time polymorphism (also called static polymorphism or early binding)
		 * is a process where the compiler decides which function or method to call
		 * during compilation based on the method signature.
		 */
	}
}

/**
 * Output:
 * Le Rect
 * -------
 * Le Rect
 * Le Rect
 */