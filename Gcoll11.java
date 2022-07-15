class Product {
	int p = 5, q = 10;

	void bill() {
		int amt = p * q;
		System.out.println(amt);
	}
};

class Gcoll11 {
	public static void main(String obj[]) {
		Product p1 = new Product();
		Product p2 = new Product();
		p2 = p1;
		p2 = null;
		p1 = null;

	}
}
