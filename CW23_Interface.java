interface Shape {
	float pi = 3.14f;

	void area(float R);
}

class Circle implements Shape {
	public void area(float R) {
		System.out.println(pi * R * R);
	}

	void dline() {
		System.out.println("==========");
	}
};

class CW23_Interface {
	public static void main(String[] args) {
		Shape sRef = new Circle();
		sRef.area(10); // runtime polymorphism happens despite area() being empty in interface because
						 // compiler see Shape ref at compile-time and at runtime the actual object is
						 // Circle, so Java invokes Circle.area() => dynamic method dispatch
		((Circle) sRef).dline(); // downcasting (def in CW20A)

		Circle cRef = (Circle) sRef; // upcasting (def in CW13B)
		cRef.dline();

		System.out.println("Hello World!");

		// An interface can extend interface(s), but cannot extend class or abstract class.
		// An abstract class can extend a class and implement interface(s).
		// 
		// Rule: Interface is like a contract, class is like implementation, 
		// and abstract class is like a partial implementation.

		/**
		 * Definition:
		 * Interface: It's a reference type that acts as strict behavioral contract
		 * which specifies method signatures a class must implement, enabling
		 * abstraction and multiple inheritance.
		 * 
		 */
	}
}
