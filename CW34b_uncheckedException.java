// custom unchecked exception
class DivModException extends RuntimeException {
    DivModException() {
        super();
    }

    DivModException(String message) {
        super(message);
    }
}

public class CW34b_uncheckedException {
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
    }

    /**
     * Output:
     * ------
     * Exception in thread "main" DivModException: Division by zero (double) not allowed
        at CW34b_uncheckedException.doDiv(CW34b_uncheckedException.java:15)
        at CW34b_uncheckedException.main(CW34b_uncheckedException.java:24)
     */
}