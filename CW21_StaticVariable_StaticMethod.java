class MATHS {
	static float pi = 3.14f;

	static float getSquare(int r) {
		return r * r;
	}
};

class CW21_StaticVariable_StaticMethod {
	public static void main(String[] args) {
		System.out.println(MATHS.pi);
		System.out.println(MATHS.getSquare(10));
	}
}
/**
 * Output:
 * -------
 * 3.14
 * 100.0
 */