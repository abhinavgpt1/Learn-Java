class JSG3 {
	public static void main(String obj[]) {
		int a = 2, b = 7;
		int x, y, z;
		x = 2;
		y = 3;
		z = x + y;
		System.out.println(z);
		float c = 2.0f;
		System.out.println(c + 3);
		float f = (float) (3.14 * 10 * 10);
		System.out.println(f);
		// float q=1.9; erroneous since, implict narrowing is not possible (double ->
		// float)
	}
}
