class Outer {
	static int y = 9;
	int r = 3;

	static void funO() {
		System.out.println("Outer Static ");

	}

	void funO2() {
		System.out.println("Innner Static");
	}

	class inner {
		void show() {
			System.out.println(y);
		}
	};
};

class DClass {
	public static void main(String[] args) {
		Outer o = new Outer();
		Outer.inner i = o.new inner();
		i.show();
		System.out.println("Hello World!");
	}
}
