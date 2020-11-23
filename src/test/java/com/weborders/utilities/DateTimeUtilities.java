package com.weborders.utilities;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtilities {
    /**
     * This method returns current date as a String
     * Provide a format as a parameter
     * MM- to specify month like: 01, 02, 03
     * MMM, Jan, Feb, Mar
     * dd- to specify day, like 01, 02, 03
     * yyyy - to specify year like: 2010, 2020
     *
     * @param format for example MMM dd, yyyy = Mar 29, 2020
     * @return current date as a String
     */
    public static String getCurrentDate(String format) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static int getHourInt(String time) {
        String splitTime = time.split(":")[0];
        return Integer.parseInt(splitTime);
    }

    /**
     * This method returns the difference between the end and the start time
     *
     * @param start time
     * @param end time
     * @param format like h:m a
     * @return difference between start and end time as a long
     */
    public static long getTimeDifference(String start, String end, String format) {
        LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern(format));
        LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern(format));
        return ChronoUnit.HOURS.between(startTime, endTime);
    }
}
