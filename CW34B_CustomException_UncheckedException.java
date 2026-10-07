class DivModException extends RuntimeException {
    DivModException() {
        super();
    }

    DivModException(String message) {
        super(message);
    }
}

public class CW34B_CustomException_UncheckedException {
    static void doDiv(double d) {
        if (d == 0.0) {
            throw new DivModException("Division by zero (double) not allowed");
        } else {
            double res = 7 / d;
            System.out.println("Output:" + res);
        }
    }

    public static void main(String[] args) {
        // unhandled exception
        doDiv(0);

        /**
         * Definitions:
         * Unchecked exceptions are runtime errors that the compiler does not force you
         * to catch or declare.
         * - Unchecked exceptions are subclasses of java.lang.RuntimeException or java.lang.Error.
         * 
         * Throwable: The root class of Java's exception hierarchy, representing objects
         * that can be thrown using throw and caught using catch. Its two main
         * subclasses are Exception and Error.
         */
    }
}

/**
 * Output:
 * ------
 * Exception in thread "main" DivModException: Division by zero (double) not allowed
 *  at CW34b_uncheckedException.doDiv(CW34b_uncheckedException.java:15)
 *  at CW34b_uncheckedException.main(CW34b_uncheckedException.java:24)
 */