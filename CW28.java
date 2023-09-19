public class CW28 {
	public static void main(String[] args) {
		System.out.println("Start");
		int d = 0;
		try {
			int res = 5 / d;
			System.out.println(res);

		} catch (ArithmeticException exp) {
			System.out.println(exp.getMessage());
			System.out.println("Division by zero not allowed, Sir");
			System.out.println(exp.toString());
		}
		System.out.println("End");
	}
}
