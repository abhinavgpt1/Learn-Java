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
		 * Definitions:
		 * Method Overloading: A compile-time polymorphism mechanism where a class
		 * defines multiple methods with the same name but different parameter lists
		 * (number, type, or order of parameters), allowing the compiler to determine
		 * which method to invoke based on the arguments passed.
		 * - Return type being same or different doesn't contribute to method
		 * overloading.
		 * 
		 * Compile-time Polymorphism: A form of polymorphism where the method to be
		 * invoked is determined by the compiler at compile time based on the method
		 * signature. It is also known as static polymorphism or early binding.
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