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

class CW9_LPV_LRV_IPV_IRV_CPV_CRV {
	public static void main(String args[]) {
		MALL mm = new MALL(); // Local Reference Variable (LRV)
		System.out.println(mm.pen.p); // 5
		mm.pen.bill(); // 50
		System.out.println(MALL.code); // 11
		MALL.lap.bill(); // 50

		// Note: Mall mm = new Mall() is wrong syntax because class names in Java are case-sensitive.

		/**
		 * Definitions:
		 * Local Primitive Variable (LPV): A primitive variable declared inside a method which is accessible only within that method.
		 * Local Reference Variable (LRV): A reference variable referencing an object declared inside a method which is accessible only within that method.
		 * Instance Primitive Variable (IPV): A non-static primitive field where each object has its own independent copy.
		 * Instance Reference Variable (IRV): A non-static reference field where each object has its own independent reference, which can refer to an object.
		 * Class Primitive Variable (CPV): A static primitive field with one shared copy per class.
		 * Class Reference Variable (CRV): A static reference field with one shared reference per class.
		 */
	}
}
