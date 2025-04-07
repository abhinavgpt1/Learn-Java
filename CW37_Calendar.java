import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CW37_Calendar {
    public static void main(String args[]) {
        Calendar calNow = Calendar.getInstance();
        System.out.println("Time now in Date.class: " + calNow.getTime());
        System.out.println();

        Calendar calTom = Calendar.getInstance();
        calTom.add(Calendar.DATE, 1);
        System.out.println("Time after 1 day (Calendar.toString()):" + calTom);
        System.out.println();
        
        System.out.println("month 0-based index: " + calNow.get(Calendar.MONTH));
        System.out.println();
        System.out.println("day of week 1-based index(SUN-to-SAT): " + calNow.get(Calendar.DAY_OF_WEEK));
        System.out.println();

        Calendar calBySetDate = Calendar.getInstance();
        calBySetDate.setTime(new Date(1000));
        System.out.println("calendar date 1 second after unix epoch by Date: " + calBySetDate.getTime());

        System.out.println("------------set functions----------");
        Calendar calSetField = Calendar.getInstance();
        calSetField.set(Calendar.MONTH, 1); // 0-based index
        System.out.println("Month set to feb(0-based index): " + calSetField.getTime());
        calSetField.set(Calendar.DATE, 31);
        System.out.println("Date set to 31st feb get rolled over ahead: " + calSetField.getTime());
        System.out.println();

        System.out.println("-----------timezone changes---------");
        // get all timezone values
        // String[]ids = TimeZone.getAvailableIDs();
        // for(String s: ids)
        // System.out.println(s);
        Calendar calCST = Calendar.getInstance(TimeZone.getTimeZone("US/Central"));
        System.out.println(calCST);
        System.out.println();

        System.out.println(calCST.getTimeZone());

        Calendar calCSTWithLocale = Calendar.getInstance(TimeZone.getTimeZone("US/Central"), Locale.FRANCE);
        // System.out.println(calCSTWithLocale); // can output
    }

    /**
     * Time now in Date.class: Sat Sep 23 17:19:52 IST 2023
     *
     * Time after 1 day (Calendar.toString()):java.util.GregorianCalendar[time=1695556192450,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.
     * calendar.ZoneInfo[id="Asia/Calcutta",offset=19800000,dstSavings=0,useDaylight=false,transitions=7,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek
     * =1,ERA=1,YEAR=2023,MONTH=8,WEEK_OF_YEAR=39,WEEK_OF_MONTH=5,DAY_OF_MONTH=24,DAY_OF_YEAR=267,DAY_OF_WEEK=1,DAY_OF_WEEK_IN_MONTH=4,AM_PM=1,HOUR=5,HOUR_OF_D
     * AY=17,MINUTE=19,SECOND=52,MILLISECOND=450,ZONE_OFFSET=19800000,DST_OFFSET=0]
     *
     * month 0-based index: 8
     * 
     * day of week 1-based index(SUN-to-SAT): 7
     * 
     * calendar date 1 second after unix epoch by Date: Thu Jan 01 05:30:01 IST 1970
     * ------------set functions----------
     * Month set to feb(0-based index): Thu Feb 23 17:19:52 IST 2023
     * Date set to 31st feb get rolled over ahead: Fri Mar 03 17:19:52 IST 2023
     * 
     * -----------timezone changes---------
     * java.util.GregorianCalendar[time=1695469792468,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="US/Central",offse
     * t=-21600000,dstSavings=3600000,useDaylight=true,transitions=235,lastRule=java.util.SimpleTimeZone[id=US/Central,offset=-21600000,dstSavings=3600000,useD
     * aylight=true,startYear=0,startMode=3,startMonth=2,startDay=8,startDayOfWeek=1,startTime=7200000,startTimeMode=0,endMode=3,endMonth=10,endDay=1,endDayOfW
     * eek=1,endTime=7200000,endTimeMode=0]],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2023,MONTH=8,WEEK_OF_YEAR=38,WEEK_OF_MONTH=4,DAY_OF_MONTH=23,
     * DAY_OF_YEAR=266,DAY_OF_WEEK=7,DAY_OF_WEEK_IN_MONTH=4,AM_PM=0,HOUR=6,HOUR_OF_DAY=6,MINUTE=49,SECOND=52,MILLISECOND=468,ZONE_OFFSET=-21600000,DST_OFFSET=3
     * 600000]
     * 
     * sun.util.calendar.ZoneInfo[id="US/Central",offset=-21600000,dstSavings=3600000,useDaylight=true,transitions=235,lastRule=java.util.SimpleTimeZone[id=US/
     * Central,offset=-21600000,dstSavings=3600000,useDaylight=true,startYear=0,startMode=3,startMonth=2,startDay=8,startDayOfWeek=1,startTime=7200000,startTim
     * eMode=0,endMode=3,endMonth=10,endDay=1,endDayOfWeek=1,endTime=7200000,endTimeMode=0]]
     */
}
