class Product {
	int p, q;

	void show() {
		System.out.println("Price:" + p + " Qty:" + q);
	}

	public String toString() {
		return ("Price:" + p + " Qty:" + q);
	}

};

class overrideTS {
	public static void main(String[] args) {
		Product lap = new Product();
		lap.p = 50000;
		lap.q = 2;
		lap.show();

		System.out.println(lap);
		System.out.println(lap.toString());
	}
}
