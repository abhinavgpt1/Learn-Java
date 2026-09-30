class CW4_ObjectMethodCall {
	public static void main(String obj[]) {
		CW4_ObjectMethodCall ref = new CW4_ObjectMethodCall();
		ref.area();
	}

	void area() {
		int l = 5, b = 10, a;
		a = l * b;
		System.out.println(a);
	}
}
