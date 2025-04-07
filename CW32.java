// Throw exception
public class CW32 {
	public static void main(String[] args) {
		System.out.println("Start");
		try {
			doDiv(0.0);
		} catch (ArithmeticException exp) {
			System.out.println(exp.toString());
		}
		System.out.println("End");
	}

	static void doDiv(double d) {
		double div;
		if (d == 0.0) {
			throw new ArithmeticException("Float division by zero not allowed, Sir");
		} else {
			div = 5 / d;
			System.out.println("Float div=" + div);
		}
	}
}

/**
 * Output
 * ------
 * Start
 * java.lang.ArithmeticException: Float division by zero not allowed, Sir
 * End
 */

/*
 * Output (without msg in ArithmeticException):
 * Start
 * java.lang.ArithmeticException
 * End
 */