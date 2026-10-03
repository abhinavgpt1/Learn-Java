import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CW37_Calendar {
    public static void main(String args[]) {
        Calendar currentCalendar = Calendar.getInstance();
        System.out.println("Time now in Date.class: " + currentCalendar.getTime());
        System.out.println();

        Calendar tomorrowsCalendar = Calendar.getInstance();
        tomorrowsCalendar.add(Calendar.DATE, 1);
        System.out.println("Time after 1 day (Calendar.toString()):" + tomorrowsCalendar);
        System.out.println();
        
        System.out.println("month 0-based index: " + currentCalendar.get(Calendar.MONTH));
        System.out.println();
        System.out.println("day of week 1-based index (SUN-to-SAT): " + currentCalendar.get(Calendar.DAY_OF_WEEK));
        System.out.println();

        Calendar fixedDateCalendar = Calendar.getInstance();
        fixedDateCalendar.setTime(new Date(1000));
        System.out.println("calendar date 1 second after unix epoch by Date: " + fixedDateCalendar.getTime());

        System.out.println("------------set functions----------");
        Calendar fieldSetCalendar = Calendar.getInstance();
        fieldSetCalendar.set(Calendar.MONTH, 1); // 0-based index
        System.out.println("Month set to feb (0-based index): " + fieldSetCalendar.getTime());
        fieldSetCalendar.set(Calendar.DATE, 31);
        System.out.println("Date set to 31st feb get rolled over ahead: " + fieldSetCalendar.getTime());
        System.out.println();

        System.out.println("-----------timezone changes---------");
        // get all timezone values
        // String[] ids = TimeZone.getAvailableIDs();
        // for (String s: ids)
        //  System.out.println(s);
        Calendar cstTimezoneCalendar = Calendar.getInstance(TimeZone.getTimeZone("US/Central"));
        System.out.println(cstTimezoneCalendar);
        System.out.println();

        System.out.println("cst timezone: " + cstTimezoneCalendar.getTimeZone());

        Calendar cstTimezoneCalendarWithLocale = Calendar.getInstance(TimeZone.getTimeZone("US/Central"), Locale.FRANCE);
        // System.out.println(cstTimezoneCalendarWithLocale); // can output
    }
}
/**
 * Output:
 * -------
 * Time now in Date.class: Sat Oct 03 16:33:24 IST 2026
 * 
 * Time after 1 day (Calendar.toString()):java.util.GregorianCalendar[time=1791111804834,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Calcutta",offset=19800000,dstSavings=0,useDaylight=false,transitions=7,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2026,MONTH=9,WEEK_OF_YEAR=41,WEEK_OF_MONTH=2,DAY_OF_MONTH=4,DAY_OF_YEAR=277,DAY_OF_WEEK=1,DAY_OF_WEEK_IN_MONTH=1,AM_PM=1,HOUR=4,HOUR_OF_DAY=16,MINUTE=33,SECOND=24,MILLISECOND=834,ZONE_OFFSET=19800000,DST_OFFSET=0]
 * 
 * month 0-based index: 9
 * 
 * day of week 1-based index (SUN-to-SAT): 7
 * 
 * calendar date 1 second after unix epoch by Date: Thu Jan 01 05:30:01 IST 1970
 * ------------set functions----------
 * Month set to feb (0-based index): Tue Feb 03 16:33:24 IST 2026
 * Date set to 31st feb get rolled over ahead: Tue Mar 03 16:33:24 IST 2026
 * 
 * -----------timezone changes---------
 * java.util.GregorianCalendar[time=1791025404842,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="US/Central",offset=-21600000,dstSavings=3600000,useDaylight=true,transitions=361,lastRule=java.util.SimpleTimeZone[id=US/Central,offset=-21600000,dstSavings=3600000,useDaylight=true,startYear=0,startMode=3,startMonth=2,startDay=8,startDayOfWeek=1,startTime=7200000,startTimeMode=0,endMode=3,endMonth=10,endDay=1,endDayOfWeek=1,endTime=7200000,endTimeMode=0]],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2026,MONTH=9,WEEK_OF_YEAR=40,WEEK_OF_MONTH=1,DAY_OF_MONTH=3,DAY_OF_YEAR=276,DAY_OF_WEEK=7,DAY_OF_WEEK_IN_MONTH=1,AM_PM=0,HOUR=6,HOUR_OF_DAY=6,MINUTE=3,SECOND=24,MILLISECOND=842,ZONE_OFFSET=-21600000,DST_OFFSET=3600000]
 * 
 * cst timezone: sun.util.calendar.ZoneInfo[id="US/Central",offset=-21600000,dstSavings=3600000,useDaylight=true,transitions=361,lastRule=java.util.SimpleTimeZone[id=US/Central,offset=-21600000,dstSavings=3600000,useDaylight=true,startYear=0,startMode=3,startMonth=2,startDay=8,startDayOfWeek=1,startTime=7200000,startTimeMode=0,endMode=3,endMonth=10,endDay=1,endDayOfWeek=1,endTime=7200000,endTimeMode=0]]
 */
