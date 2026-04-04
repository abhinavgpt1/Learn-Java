public class ValueRollover {
    public static void main(String[] args) {
        long l = Integer.MAX_VALUE + 10; // != 2147483657, but is -2147483639 since value on RHS is integer by default.
        System.out.println(l);

        int x = Math.abs(Integer.MIN_VALUE);
        System.out.println(x); // = MIN_VALUE
        // Reason: Math.abs() works as a < 0 ? -a : a
        // So, - (-2147483648) = +2147483648 = rollover of MAX_VALUE by 1 => -2147483648
    }
}

/**
 * Output:
 * -------
 * -2147483639
 * -2147483648
 */