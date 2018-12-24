package com.github.skonline90.view;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.github.skonline90.controller.DurationCalculator;
import com.github.skonline90.exceptions.TimeException;
import com.github.skonline90.model.TypedDuration;

/**
 * This class is used for user interaction via the command prompt.
 *
 * @author skonline90
 * @version 24.12.2018
 */
public class Console
{
    /*
     * Constants.
     */
    private static final String DATE_TIME_FORMAT = "dd.MM.uuuu - HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
        .ofPattern(DATE_TIME_FORMAT);
    private static final String NEW_LINE = System.lineSeparator();
    private static final String PROMPT = "> ";

    /*
     * Fields.
     */
    private Scanner scanner;
    private String input;
    private boolean isInputWrong;

    /**
     * Constructor.
     */
    public Console()
    {
        this.scanner = new Scanner(System.in);
        this.input = "";
        this.isInputWrong = false;
    }

    public void startDialog()
    {
        sendWelcomeMessage();

        // start the loop
        do
        {
            System.out.println(NEW_LINE + "Choose one of the following:"
                    + NEW_LINE
                    + "0    - Calculate duration between two points in time"
                    + NEW_LINE
                    + "1    - Calculate point in time after a certain duration"
                    + NEW_LINE
                    + "quit - End the program (command works at any time)");

            do
            {
                // initial user choice
                if (isInputWrong)
                    System.out.println("Enter a valid command ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!(input.equals("0") || input.equals("1")
                    || input.toLowerCase()
                        .equals("quit")));
            isInputWrong = false;

            // user wants the duration between two dates
            if (input.equals("0"))
            {
                initiateDurationCalculationSequenceDialog();
            }
            // user wants the date after a certain duration
            else if (input.equals("1"))
            {
                initiateDateCalculationSequenceDialog();
            }
            else
            {
                quit();
            }

            askForLoopRestart();
        }
        while (true);

    }

    private void sendWelcomeMessage()
    {
        System.out.println("Welcome to the duration calculator!");
    }

    private void prompt()
    {
        System.out.print(PROMPT);
    }

    private void initiateDurationCalculationSequenceDialog()
    {
        LocalDateTime dateA = null, dateB = null;
        long durationInSeconds = 0;
        boolean userEnteredBadTimeLogic;

        do
        {
            userEnteredBadTimeLogic = false;
            System.out.println(NEW_LINE
                    + "Enter the first point in time. It has to be prior"
                    + " or equal to the second point in time.");
            System.out.println(
                    "Choose now?" + NEW_LINE + "0 - Yes" + NEW_LINE + "1 - No");
            do
            {
                if (isInputWrong)
                    System.out.println("Enter a valid command ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!(input.equals("0") || input.equals("1")
                    || input.toLowerCase()
                        .equals("quit")));
            isInputWrong = false;

            // now
            if (input.equals("0"))
            {
                dateA = LocalDateTime.now();
            }
            // ask for input
            else if (input.equals("1"))
            {
                dateA = enterDateDetails();
            }
            // quit
            else
            {
                quit();
            }

            System.out.println(NEW_LINE
                    + "Enter the second point in time. It has to be after"
                    + " or equal to the first point in time." + NEW_LINE
                    + "Choose now?" + NEW_LINE + "0 - Yes" + NEW_LINE
                    + "1 - No");
            do
            {
                if (isInputWrong)
                    System.out.println("Enter a valid command ...");
                input = scanner.next();
                isInputWrong = true;
            }
            while (!(input.equals("0") || input.equals("1")
                    || input.toLowerCase()
                        .equals("quit")));
            isInputWrong = false;

            // now
            if (input.equals("0"))
            {
                dateB = LocalDateTime.now();
            }
            // ask for input
            else if (input.equals("1"))
            {
                dateB = enterDateDetails();
            }
            // quit
            else
            {
                quit();
            }

            try
            {
                durationInSeconds = DurationCalculator
                    .calculateSecondsBetweenTwoPointsInTime(dateA, dateB);
            }
            catch (TimeException e)
            {
                // causes the user to reenter the date values
                userEnteredBadTimeLogic = true;
                System.out.println(
                        "The second date that was entered is prior to the first one. Reenter the dates.");
            }
        }
        while (userEnteredBadTimeLogic);

        // create a pretty string for the result
        String s = "The duration between " + dateA.format(FORMATTER) + " and "
                + dateB.format(FORMATTER) + " is:";
        String result = NEW_LINE
                + DurationCalculator.convertDuationToString(durationInSeconds);

        StringBuilder lineBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            lineBuilder.append("~");
        }

        System.out.print(NEW_LINE);
        System.out.println(lineBuilder.toString() + NEW_LINE + s + result
                + NEW_LINE + lineBuilder.toString());
        ;
    }

    private void initiateDateCalculationSequenceDialog()
    {
        LocalDateTime dateA = null;
        TypedDuration duration = null;

        System.out.println(NEW_LINE + "Enter the starting date.");
        System.out.println(
                "Choose now?" + NEW_LINE + "0 - Yes" + NEW_LINE + "1 - No");
        do
        {
            if (isInputWrong) System.out.println("Enter a valid command ...");
            prompt();
            input = scanner.next();
            isInputWrong = true;
        }
        while (!(input.equals("0") || input.equals("1") || input.toLowerCase()
            .equals("quit")));
        isInputWrong = false;

        // now
        if (input.equals("0"))
        {
            dateA = LocalDateTime.now();
        }
        // ask for input
        else if (input.equals("1"))
        {
            dateA = enterDateDetails();
        }
        // quit
        else
        {
            quit();
        }

        duration = enterDurationDetails();
        LocalDateTime dateTime = DurationCalculator
            .calculateDateTimeAfterDuration(dateA, duration.getDuration());

        // create a pretty string for the result
        System.out.print(NEW_LINE);
        String parameters = "The start date was " + dateA.format(FORMATTER)
                + ". The duration was " + duration.getQuantity() + " "
                + duration.getQuantityUnit() + ".";
        String result = NEW_LINE + "The resulting date is: "
                + dateTime.format(FORMATTER) + NEW_LINE;
        StringBuilder lineBuilder = new StringBuilder();
        for (int i = 0; i < parameters.length(); i++)
        {
            lineBuilder.append("~");
        }
        System.out.println(lineBuilder.toString() + NEW_LINE + parameters
                + result + lineBuilder.toString());
        ;
    }

    private LocalDateTime enterDateDetails()
    {
        int year, month, day, hour, minute, second;
        boolean isDateValid;
        LocalDateTime returnDateTime = null;

        do
        {
            isDateValid = true;
            System.out.println(NEW_LINE + "Enter the YEAR of the date: ");
            do
            {
                if (isInputWrong) System.out.println("Enter a valid YEAR ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!((input.matches("\\d{1,4}") && Integer.parseInt(input) >= 0)
                    || input.equals("quit")));
            if (input.equals("quit")) quit();
            year = Integer.parseInt(input);
            isInputWrong = false;

            System.out.println(NEW_LINE + "Enter the MONTH of the date: ");
            do
            {
                if (isInputWrong) System.out.println("Enter a valid MONTH ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!((input.matches("\\d{1,2}") && Integer.parseInt(input) > 0
                    && Integer.parseInt(input) <= 12) || input.equals("quit")));
            if (input.equals("quit")) quit();
            month = Integer.parseInt(input);
            isInputWrong = false;

            System.out.println(NEW_LINE + "Enter the DAY of the date: ");
            do
            {
                if (isInputWrong) System.out.println("Enter a valid DAY ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!((input.matches("\\d{1,2}") && Integer.parseInt(input) > 0
                    && Integer.parseInt(input) <= 31) || input.equals("quit")));
            if (input.equals("quit")) quit();
            day = Integer.parseInt(input);
            isInputWrong = false;

            System.out.println(NEW_LINE + "Enter the HOUR of the date: ");
            do
            {
                if (isInputWrong) System.out.println("Enter a valid HOUR ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!((input.matches("\\d{1,2}") && Integer.parseInt(input) >= 0
                    && Integer.parseInt(input) <= 23) || input.equals("quit")));
            if (input.equals("quit")) quit();
            hour = Integer.parseInt(input);
            isInputWrong = false;

            System.out.println(NEW_LINE + "Enter the MINUTE of the date: ");
            do
            {
                if (isInputWrong)
                    System.out.println("Enter a valid MINUTE ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!((input.matches("\\d{1,2}") && Integer.parseInt(input) >= 0
                    && Integer.parseInt(input) <= 59) || input.equals("quit")));
            if (input.equals("quit")) quit();
            minute = Integer.parseInt(input);
            isInputWrong = false;

            System.out.println(NEW_LINE + "Enter the SECOND of the date: ");
            do
            {
                if (isInputWrong)
                    System.out.println("Enter a valid SECOND ...");
                prompt();
                input = scanner.next();
                isInputWrong = true;
            }
            while (!((input.matches("\\d{1,2}") && Integer.parseInt(input) >= 0
                    && Integer.parseInt(input) <= 59) || input.equals("quit")));
            if (input.equals("quit")) quit();
            second = Integer.parseInt(input);
            isInputWrong = false;

            try
            {
                returnDateTime = LocalDateTime.of(year, month, day, hour,
                        minute, second);
            }
            catch (DateTimeException e)
            {
                isDateValid = false;
                System.out.println(
                        "The entered date does not exist. Enter a valid date and time.");
            }
        }
        while (!isDateValid);

        return returnDateTime;
    }

    public TypedDuration enterDurationDetails()
    {
        System.out.println(NEW_LINE + "Choose the unit for the duration:"
                + NEW_LINE + "0 - Seconds" + NEW_LINE + "1 - Minutes" + NEW_LINE
                + "2 - Hours" + NEW_LINE + "3 - days");
        do
        {
            if (isInputWrong) System.out.println("Enter a valid command ...");
            prompt();
            input = scanner.next();
            isInputWrong = true;
        }
        while (!(input.equals("0") || input.equals("1") || input.equals("2")
                || input.equals("3") || input.toLowerCase()
                    .equals("quit")));
        if (input.equals("quit")) quit();
        isInputWrong = false;
        int option = Integer.parseInt(input);

        System.out.println(NEW_LINE + "Enter the quantity: ");
        do
        {
            if (isInputWrong) System.out.println("Enter a valid QUANTITY ...");
            prompt();
            input = scanner.next();
            isInputWrong = true;
        }
        while (!((input.matches("\\d{1,20}") && Integer.parseInt(input) >= 0)
                || input.toLowerCase()
                    .equals("quit")));
        if (input.equals("quit")) quit();
        isInputWrong = false;

        long quantity = Long.parseLong(input);
        Duration duration;
        String unit;
        if (option == 0)
        {
            duration = Duration.ofSeconds(quantity);
            unit = "seconds";
        }
        else if (option == 1)
        {
            duration = Duration.ofMinutes(quantity);
            unit = "minutes";
        }
        else if (option == 2)
        {
            duration = Duration.ofHours(quantity);
            unit = "hours";
        }
        else
        {
            duration = Duration.ofDays(quantity);
            unit = "days";
        }

        TypedDuration result = new TypedDuration(duration, quantity, unit);
        return result;
    }

    private void askForLoopRestart()
    {
        System.out.println(NEW_LINE + "Do you require more calculations?"
                + NEW_LINE + "0 - Yes" + NEW_LINE + "1 - No");
        do
        {
            if (isInputWrong) System.out.println("Enter a valid command ...");
            prompt();
            input = scanner.next();
            isInputWrong = true;
        }
        while (!(input.equals("0") || input.equals("1") || input.toLowerCase()
            .equals("quit")));

        if (!input.equals("0")) quit();
        isInputWrong = false;
    }

    private void quit()
    {
        scanner.close();
        System.out.println("Bye!");
        System.exit(0);
    }
}
