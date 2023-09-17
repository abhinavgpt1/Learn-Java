class MATHS {
	static float pi = 3.14f;

	static float getSquare(int r) {
		return r * r;
	}
};

class Use {
	public static void main(String[] args) {
		System.out.println(MATHS.pi);
		System.out.println(MATHS.getSquare(10));
	}
}
