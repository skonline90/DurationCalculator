package com.github.skonline90.exceptions;

/**
 * A time exception is thrown when a duration is calculated. It
 * happens when the end date is chronologically before the start date.
 *
 * @author skonline90
 * @version 24.12.2018
 */
public class TimeException extends Exception
{
    private static final long serialVersionUID = 1L;

    public TimeException(String message)
    {
        super(message);
    }
}
