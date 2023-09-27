import java.util.Date;

public class tech11_timeToPrintIncrementalDate {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        // increment time in seconds
        for (int i = 0; i <= 10; i++) {
            System.out.println(new Date((new Date().getTime() + i * 1000)));
        }
        long end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - start) + " milliseconds");
    }

    /**
     * Sat Sep 23 17:16:50 IST 2023
     * Sat Sep 23 17:16:51 IST 2023
     * Sat Sep 23 17:16:52 IST 2023
     * Sat Sep 23 17:16:53 IST 2023
     * Sat Sep 23 17:16:54 IST 2023
     * Sat Sep 23 17:16:55 IST 2023
     * Sat Sep 23 17:16:56 IST 2023
     * Sat Sep 23 17:16:57 IST 2023
     * Sat Sep 23 17:16:58 IST 2023
     * Sat Sep 23 17:16:59 IST 2023
     * Sat Sep 23 17:17:00 IST 2023
     * Time elapsed: 62 milliseconds
     */
}
