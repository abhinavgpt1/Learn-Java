// custom checked exception
class DivModException extends Exception {
	DivModException() {
		super();
	}

	DivModException(String message) {
		super(message);
	}
}

public class CW34a_checkedException {
	static void doDiv(double denominator) throws DivModException {
		if (denominator == 0.0) {
			throw new DivModException("Division by 0(double) not allowed");
		}
		double res = 7 / denominator;
		System.out.println("Div output: " + res);
	}

	static void doMod(int mod) throws DivModException {
		if (mod == 0) {
			throw new DivModException(); // null as message
		}
		int res = 7 % mod;
		System.out.println("Mod output: " + res);
	}

	public static void main(String[] args) {
		try {
			doDiv(0.0);
		} catch (DivModException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			try {
				doMod(0);
			} catch (Exception exc) {
				System.out.println(exc.getMessage());
				exc.printStackTrace();
			}
		}
	}

	/**
	 * Output:
	 * -------
	 * Division by 0(double) not allowed
	 * DivModException: Division by 0(double) not allowed
	 *         at CW34a_checkedException.doDiv(CW34a_checkedException.java:15)
	 *         at CW34a_checkedException.main(CW34a_checkedException.java:31)
	 * null
	 * DivModException
	 *         at CW34a_checkedException.doMod(CW34a_checkedException.java:23)
	 *         at CW34a_checkedException.main(CW34a_checkedException.java:36)
	 */
}
