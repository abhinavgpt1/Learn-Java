public class CW29_UncaughtException_FinallyBlock {
	public static void main(String[] args) {
		int a[] = { 4, 8, 10 };
		int d = 2;
		System.out.println("Start");
		try {
			int val = a[d];
			System.out.println("Array value =" + val);
			int dv = val / d;
			System.out.println("div =" + dv);
			CW29 ref = null;
			ref.toString();
		} catch (ArithmeticException exp) {
			System.out.println(exp.getMessage());
		} catch (ArrayIndexOutOfBoundsException exp) {
			System.out.println("Invalid Index");
		} finally {
			System.out.println("Finally executed: urgent");
		}
		System.out.println("End"); // not printed since exception is thrown and not caught
	}
}
/**
 * Output:
 * Start
 * Array value =10
 * div =5
 * Finally executed: urgent
 * Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Object.toString()" because "<local5>" is null
 * 	at CW29_UncaughtException_FinallyBlock.main(CW29_UncaughtException_FinallyBlock.java:12)
 */
