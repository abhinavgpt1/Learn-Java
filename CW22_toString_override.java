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

		// Rule: Signature of toString() method in Object.class = "public String toString()"

		/**
		 * Definitions:
		 * toString(): A method from Object class that returns a string representation
		 * of an object. It is commonly overridden to provide meaningful object information.
		 */
	}
}
