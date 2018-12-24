package com.github.skonline90.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.github.skonline90.exceptions.TimeException;

/**
 * Use to calculate duration between two points in time
 * or to calculate the date after a certain duration.
 * 
 * A duration is a amount of time. It can be measured in
 * days, hours, minutes, seconds or combinations of 
 * those units.
 *
 * @author skonline90
 * @version 23.12.2018
 */
public class DurationCalculator
{
    /**
     * Calculates the duration between two points in time.
     * Point A has to be the same or earlier than point B.
     * 
     * @param a Starting point in time.
     * @param b Ending point in time.
     * 
     * @return Duration between A and B.
     * 
     * @throws TimeException If B is before A.
     */
    public static long calculateSecondsBetweenTwoPointsInTime(LocalDateTime a,
            LocalDateTime b) throws TimeException
    {
        if (b.isBefore(a))
            throw new TimeException("The point b is earlier than the point a.");

        return a.until(b, ChronoUnit.SECONDS);
    }

    public static LocalDateTime calculateDateTimeAfterDuration(LocalDateTime a,
            Duration d)
    {
        return a.plus(d);
    }

    public static String convertDuationToString(long seconds)
    {
        StringBuilder builder = new StringBuilder();

        long days, hours, minutes;
        if (seconds > 0)
        {
            days = seconds / 86400;
            seconds = seconds % 86400;

            hours = seconds / 3600;
            seconds = seconds % 3600;

            minutes = seconds / 60;
            seconds = seconds % 60;

            String newLine = System.lineSeparator();
            if (days > 0) builder.append("Days: " + days + newLine);
            if (hours > 0) builder.append("Hours: " + hours + newLine);
            if (minutes > 0) builder.append("Minutes: " + minutes + newLine);
            if (seconds > 0) builder.append("Seconds: " + seconds);
        }
        else
        {
            builder.append("Seconds: 0");
        }

        return builder.toString();
    }
}
