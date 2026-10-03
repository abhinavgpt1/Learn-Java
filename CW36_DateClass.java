import java.util.Date;

public class CW36_DateClass {
    public static void main(String args[]) {
        Date d1 = new Date(); // Current date and time
        System.out.println("Current datetime: " + d1);

        long oneDayInMilliseconds = 24 * 60 * 60 * 1000;
        Date d2 = new Date(d1.getTime() - oneDayInMilliseconds); // Date accepting milliseconds
        System.out.println("Datetime one day before: " + d2);

        Date dUnixEpoch = new Date(0); // Unix epoch time (January 1, 1970, 00:00:00 GMT)
        System.out.println("Unix epoch datetime w.r.t. timezone: " + dUnixEpoch);

        Date dtestMillisecondStorage = new Date(100); // 01-01-1970 00:00:00.100 GMT
        System.out.println("Date with no millisecond on sout: " + dtestMillisecondStorage);
        System.out.println("Milliseconds stored internally: " + (dtestMillisecondStorage.getTime() - dUnixEpoch.getTime()));

        Date dConstructor = new Date(2023 - 1900, 1, 28); // month is 0-indexed // 2023-02-28
        System.out.println("Deprecated constructor: " + dConstructor);

        Date dConstructorWithRolloverDate = new Date(2023 - 1900, 1, 31); // 31Feb 2023 is invalid, so it rolls over to 03Mar2023
        System.out.println("Deprecated constructor with date rolled over: " + dConstructorWithRolloverDate);
        
        Date dateNegativeMilliseconds = new Date(-1000);
        System.out.println("Date with negative millisecond: " + dateNegativeMilliseconds); // 1sec minus epoch time = Thu Jan 01 05:29:59 IST 1970
    }

    /**
     * Output:
     * -------
     * Note: CW36_DateClass.java uses or overrides a deprecated API.
     * Note: Recompile with -Xlint:deprecation for details.
     * Current datetime: Sat Oct 03 16:29:15 IST 2026
     * Datetime one day before: Fri Oct 02 16:29:15 IST 2026
     * Unix epoch datetime w.r.t. timezone: Thu Jan 01 05:30:00 IST 1970
     * Date with no millisecond on sout: Thu Jan 01 05:30:00 IST 1970
     * Milliseconds stored internally: 100
     * Deprecated constructor: Tue Feb 28 00:00:00 IST 2023
     * Deprecated constructor with date rolled over: Fri Mar 03 00:00:00 IST 2023
     * Date with negative millisecond: Thu Jan 01 05:29:59 IST 1970
     */
}