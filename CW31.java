// Exception handling after exception handling
public class CW31 {
	public static void main(String[] args) {
		System.out.println("Start");
		try {
			doDiv(0);
			System.out.println("sljflskd");
		} catch (ArrayIndexOutOfBoundsException exp) {
			exp.printStackTrace();
		}
		System.out.println("End");
	}

	static void doDiv(int d) {
		try {
			int val = 5 / d;
			System.out.println("div=" + val);
		} catch (ArithmeticException exp) {
			exp.printStackTrace();
		}
		int ary[] = { 4, 6 };
		System.out.println(ary[10]);
		System.out.println("In doDiv()");
	}
}

/**
 * Output:
 * Start
 * java.lang.ArithmeticException: / by zero
 * 	at CW31.doDiv(CW31.java:16)
 * 	at CW31.main(CW31.java:6)
 * java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 2
 * 	at CW31.doDiv(CW31.java:22)
 * 	at CW31.main(CW31.java:6)
 * End
 */