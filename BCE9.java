class Product {
	int p = 5, q = 10; // Instance Primitive Variable (IPV)

	void bill() {
		int amt = p * q; // Local Primitive Variable (LPV)
		System.out.println(amt);
	}
};

class MALL {
	Product pen = new Product(); // Instance Reference Variable (IRV)
	static int code = 11; // Class Primitive Variable (CPV)
	static Product lap = new Product(); // Class Reference Variable (CRV)
};

class BCE9 {
	public static void main(String args[]) {
		MALL mm = new MALL(); // Local Reference Variable (LRV)
		System.out.println(mm.pen.p);
		mm.pen.bill();
		System.out.println(MALL.code);
		MALL.lap.bill();

		/**
		 * Definitions:
		 * Local Primitive Variable (LPV): A primitive variable declared inside a method; accessible only within that method.
		 * Local Reference Variable (LRV): A reference variable referencing an object declared inside a method; accessible only within that method.
		 * Instance Primitive Variable (IPV): A non-static primitive field; each object has its own copy.
		 * Instance Reference Variable (IRV): A non-static reference field; each object has its own reference, which can refer to an object.
		 * Class Primitive Variable (CPV): A static primitive field; one shared copy exists for the entire class.
		 * Class Reference Variable (CRV): A static reference field; one shared reference exists for the entire class.
		 */
	}
}
