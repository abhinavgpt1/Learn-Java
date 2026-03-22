// FYI: An abstract class can have a constructor in Java, which is automatically invoked when a concrete subclass is instantiated. 
// While an abstract class cannot be directly instantiated, its constructor serves to initialize fields of its class & perform setup tasks for its subclasses.

// An interface can extend interface(s), but cannot extend class or abstract class.
// An abstract class can extend a class and implement interface(s).
// Interface is like a contract, class is like implementation, and abstract class is like a partial implementation.

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

class Abstract {
	public static void main(String[] args) {
		Rect robj = new Rect();
		robj.area();
		robj.line();
		robj.dline();
		Shape ref = new Rect();
		ref.area();
		ref.line();
		((Rect) ref).dline();
		System.out.println("Hello World!");
	}
}
