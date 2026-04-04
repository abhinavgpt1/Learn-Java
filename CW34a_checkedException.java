// custom checked exception
class DivModException extends Exception {
	DivModException() {
		super();
	}

	DivModException(String message) {
		super(message);
	}
}

// Trick qq: How to check if an exception is checked or unchecked at runtime?
// Ans: Check if it is an instance of java.lang.RuntimeException or java.lang.Error
/**
 * public static boolean isChecked(Throwable e) {
 * return !(e instanceof RuntimeException || e instanceof Error);
 * }
 */

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
		// NOTE: try-with-resources works on classes which implement AutoCloseable => no need for finally.
		try {
			doDiv(0.0);
		} catch (DivModException ex) {
			System.out.println("doDiv exception message: " + ex.getMessage());
			ex.printStackTrace();
			try {
				doMod(0);
			} catch (Exception exc) {
				System.out.println("doMod exception message: " + exc.getMessage());
				exc.printStackTrace();
			}
		}
	}
}

/**
 * Output:
 * -------
 * doDiv exception message: Division by 0(double) not allowed
 * doMod exception message: null
 * DivModException: Division by 0(double) not allowed
 * 	at CW34a_checkedException.doDiv(CW34a_checkedException.java:26)
 * 	at CW34a_checkedException.main(CW34a_checkedException.java:42)
 * DivModException
 * 	at CW34a_checkedException.doMod(CW34a_checkedException.java:34)
 * 	at CW34a_checkedException.main(CW34a_checkedException.java:47)
 */


/**
 * Note: stackTrace of doDiv is printed later than doMod exceptio message 
 * - tldr; Reason: there are 2 streams working simultaneously - stdout and stderr
 * 
 * [Copilot]
 * The key issue here is output stream buffering. Let me explain the execution flow and why the output appears out of order:
 * Execution Order (what actually happens):
 * Line 44: System.out.println("doDiv exception message...") → writes to stdout
 * Line 45: ex.printStackTrace() → writes to stderr
 * Line 46: doMod(0) is called → throws exception
 * Line 48: System.out.println("doMod exception message...") → writes to stdout
 * Line 49: exc.printStackTrace() → writes to stderr
 * 
 * Why Stack Traces Appear Last:
 * printStackTrace() writes to stderr by default, while System.out.println() writes to stdout. These are two separate streams:
 * 	System.out (println): typically buffered line-by-line
 * 	System.err (printStackTrace): often immediately flushed, but depending on the Java/OS implementation and whether standard streams are being redirected, the buffering behavior can differ
 * 
 * The console may display stdout first (both messages), then flush stderr (both stack traces), rather than interleaving them by actual execution order.
 * 
 * To Verify This and Control Output:
 * If you want stdout and stderr to appear in strict execution order, you can redirect both to stdout:
 * 	- ex.printStackTrace(System.out);  // Write to stdout instead of stderr
 */