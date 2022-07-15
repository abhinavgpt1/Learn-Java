class RECT {
	void Rarea() {
		System.out.println("Le Rect");
	}
};

class Circle extends RECT {
	void Carea() {
		System.out.println("Le Circle");
	}
};

class Inh {
	public static void main(String[] args) {
		Circle obj = new Circle();
		obj.Rarea();
		obj.Carea();

	}
}
