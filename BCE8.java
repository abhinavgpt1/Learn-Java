class product2 {
	int price, qty;

	void show() {
		int z = 100;
		System.out.println(z);
	}

};

class BCE8 {
	public static void main(String[] args) {
		product2 ref = new product2();
		int x = 7;
		System.out.println(ref.price);
		ref.price = 5;
		ref.qty = 10;
		System.out.println(ref.price * ref.qty);
	}
}
