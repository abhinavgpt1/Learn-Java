import java.util.Date;

public class CW36 {
    public static void main(String args[]) {
        Date d1 = new Date();
        System.out.println("Current datetime: " + d1);

        long oneDayInMilliseconds = 24 * 60 * 60 * 1000;
        Date d2 = new Date(d1.getTime() - oneDayInMilliseconds);
        System.out.println("Datetime one day before: " + d2);

        Date dUnixEpoch = new Date(0);
        System.out.println("Unix epoch datetime w.r.t. timezone: " + dUnixEpoch);

        Date dtestMillisecondStorage = new Date(100);
        System.out.println("Date with no millisecond on SOP: " + dtestMillisecondStorage);
        System.out.println(
                "Milliseconds stored internally: " + (dtestMillisecondStorage.getTime() - dUnixEpoch.getTime()));

        Date dConstructor = new Date(2023 - 1900, 1, 28);
        System.out.println("Deprecated constructor: " + dConstructor);

        Date dConstructorWithExtendedDate = new Date(2023 - 1900, 1, 31);
        System.out.println("Deprecated constructor with date extended: " + dConstructorWithExtendedDate);
    }

    /**
     * Output:
     * -------
     * Note: CW36.java uses or overrides a deprecated API.
     * Note: Recompile with -Xlint:deprecation for details.
     * Current datetime: Sat Sep 23 15:41:10 IST 2023
     * Datetime one day before: Fri Sep 22 15:41:10 IST 2023
     * Unix epoch datetime w.r.t. timezone: Thu Jan 01 05:30:00 IST 1970
     * Date with no millisecond on SOP: Thu Jan 01 05:30:00 IST 1970
     * Milliseconds stored internally: 100
     * Deprecated constructor: Tue Feb 28 00:00:00 IST 2023
     * Deprecated constructor with date extended: Fri Mar 03 00:00:00 IST 2023
     */
}