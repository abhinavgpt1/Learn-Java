interface Shape {
	void draw();
}

class Rect implements Shape {
	public void draw() {
		System.out.println("Area of Rect");
	}
};

class Circle implements Shape {
	public void draw() {
		System.out.println("Area of Circle");
	}
};

class Robot {
	void drawItNow(Shape shapeRef) {
		shapeRef.draw();
	}

	Shape getObject(String shapeHindi) {
		if (shapeHindi.equals("dibba")) {
			Rect rRef = new Rect();
			return rRef;
		} else if (shapeHindi.equals("gola")) {
			return new Circle();
		} else {
			return null;
		}
	}

};

class CW24_PolymorphicArgumentPolymorphicReturn_PAPR {
	public static void main(String[] args) {
		Robot robot = new Robot();

		// Polymorphic Argument: The method drawItNow() accepts a reference of type
		// Shape, which can refer to any object that implements the Shape interface.
		// This allows the method to be called with different types of shapes (Rect,
		// Circle, etc.) without needing to overload the method for each shape type.
		Rect rect1 = new Rect();
		Circle circle1 = new Circle();
		robot.drawItNow(rect1);
		robot.drawItNow(circle1);
		System.out.println();
		
		// Polymorphic Return: The method getObject() returns a reference of type
		// Shape, which can refer to any object that implements the Shape interface.
		// This allows the method to return different types of shapes (Rect, Circle,
		// etc.) without needing to overload the method for each shape type.
		Rect rect2 = (Rect) robot.getObject("dibba");
		rect2.draw();

		Shape shape = robot.getObject("gola");
		shape.draw(); // runtime polymorphism (overriding + upcasting, ref: CW13B)

		// Risk of downcasting: If the object returned by getObject() is not actually a
		// Circle, this cast will throw a ClassCastException at runtime.
		Circle cRef = (Circle) shape; 
		cRef.draw();
	}
}

/**
 * Output:
 * -------
 * Area of Rect
 * Area of Circle
 * 
 * Area of Rect
 * Area of Circle
 * Area of Circle
 */