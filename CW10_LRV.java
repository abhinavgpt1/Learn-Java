class Product {
	int p = 5, q = 10; // Instance Primitive Variable (IPV)

	void bill() {
		int amt = p * q;
		System.out.println(amt);
	}
};

class MALL {
	void billing() {
		Product pen = new Product(); // Local Reference Variable (LRV)
		pen.bill();
		// static int code = 11; // error: static variable cannot be declared inside a method
	}
};

class CW10_LRV {
	public static void main(String[] args) {
		MALL mm = new MALL();
		mm.billing();
		// Note: Mall mm = new Mall() is wrong syntax because class names in Java are case-sensitive.
	}
}

/**
 * Output:
 * 50
 */