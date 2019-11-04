package ua.nure.prykhodko.utils;

import ua.nure.prykhodko.entity.Station;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static String parseDate(Timestamp timestamp) {
        String dateTime = timestamp.toString();
        String[] parsedString = dateTime.split("\\s");
        String time = parsedString[1];
        return time;
    }

    public static String countTimeInRoad(Station stationTo, Station stationFrom) {
        StringBuilder sb = new StringBuilder();
        Long timeInRoad = stationTo.getArrive_time().getTime() - stationFrom.getDepart_time().getTime();

        timeInRoad /= 60000;
        long hours = 0;
        long minutes = 0;
        hours = timeInRoad / 60;
        minutes = timeInRoad % 60;

        sb.append(hours).append(":").append(minutes);

        return sb.toString();
    }

    public static boolean compareDayOfWeek(Timestamp first, Timestamp second) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date d = null;
        Date d2 = null;
        try {
            d = sdf.parse(first.toString());
            d2 = sdf.parse(second.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(d);
        cal2.setTime(d2);
        return cal.get(Calendar.DAY_OF_WEEK) == cal2.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean timeFilter(Timestamp time, Timestamp routeTime) {
        String time_str = parseDate(time);
        String routeTime_str = parseDate(routeTime);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        sb.append("1970-01-01").append(" ").append(time_str);
        sb1.append("1970-01-01").append(" ").append(routeTime_str);
        Long timeDiff = Timestamp.valueOf(sb1.toString()).getTime() - Timestamp.valueOf(sb.toString()).getTime();
        return timeDiff > 0;
    }

    public static boolean compareTimeSation(Timestamp to, Timestamp from) {
        Long time = to.getTime() - from.getTime();
        return time > 0;
    }

}
