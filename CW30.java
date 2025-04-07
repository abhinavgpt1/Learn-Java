public class CW30 {
	public static void main(String[] args) {
		System.out.println("Start");
		doDiv(0);
		System.out.println("End");
	}

	static void doDiv(int d) {
		try {
			int val = 5 / d;
			System.out.println("div=" + val);
		} catch (ArithmeticException exp) {
			System.out.println(exp.toString());
		}
	}
}
/**
 * Output:
 * Start
 * java.lang.ArithmeticException: / by zero
 * End
 */