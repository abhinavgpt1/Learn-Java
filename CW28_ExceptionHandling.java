public class CW28_ExceptionHandling {
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

/**
 * Output:
 * Start
 * / by zero
 * Division by zero not allowed, Sir
 * java.lang.ArithmeticException: / by zero
 * End
 */