// An interface can extend interface(s), but cannot extend class or abstract class.
// An abstract class can extend a class and implement interface(s).
// Interface is like a contract, class is like implementation, and abstract class is like a partial implementation.

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

class Interface {
	public static void main(String[] args) {
		Shape sRef = new Circle();
		sRef.area(10);
		((Circle) sRef).dline();
		Circle cRef = (Circle) sRef;
		cRef.dline();

		System.out.println("Hello World!");
	}
}
