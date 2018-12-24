package com.github.skonline90.view;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.github.skonline90.controller.DurationCalculator;
import com.github.skonline90.exceptions.TimeException;

public class Console
{
    private static final String DATE_TIME_FORMAT = "dd.MM.uuuu - HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
        .ofPattern(DATE_TIME_FORMAT);

    private Scanner scanner;
    private String input;

    private String nline;
    private String sep;

    public Console()
    {
        this.scanner = new Scanner(System.in);
        this.input = "";
        this.nline = System.lineSeparator();
        this.sep = "------------------------------";
    }

    public void startDialog() throws TimeException
    {
        sendWelcomeMessage();

        LocalDateTime dateA = null, dateB = null;
        Duration duration = null;
        boolean wrongInput = false;

        // start the loop
        do
        {
            System.out.println("Choose one of the following:" + nline
                    + "0    - Calculate duration between two points in time"
                    + nline
                    + "1    - Calculate point in time after a certain duration"
                    + nline
                    + "quit - End the program (command works at any time)");

            do
            {
                // wait for useres initial choice
                if (wrongInput) System.out.println("Enter a valid command ...");
                input = scanner.next();
                wrongInput = true;
            }
            while (!(input.equals("0") || input.equals("1")
                    || input.toLowerCase()
                        .equals("quit")));
            wrongInput = false;

            // user wants the duration between two dates
            if (input.equals("0"))
            {
                if (wrongInput) System.out.println("Enter a valid command ...");
                System.out.println(nline
                        + "Enter the first point in time. It has to be prior"
                        + " or equal to the second point in time.");

                System.out.println(nline + "Choose now?" + nline + "0 - Yes"
                        + nline + "1 - No");
                do
                {
                    if (wrongInput)
                        System.out.println("Enter a valid command ...");
                    input = scanner.next();
                    wrongInput = true;
                }
                while (!(input.equals("0") || input.equals("1")
                        || input.toLowerCase()
                            .equals("quit")));
                wrongInput = false;

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

                System.out.println(
                        "Enter the second point in time. It has to be after"
                                + " or equal to the first point in time.");

                System.out.println(nline + "Choose now?" + nline + "0 - Yes"
                        + nline + "1 - No");
                do
                {
                    if (wrongInput)
                        System.out.println("Enter a valid command ...");
                    input = scanner.next();
                    wrongInput = true;
                }
                while (!(input.equals("0") || input.equals("1")
                        || input.toLowerCase()
                            .equals("quit")));
                wrongInput = false;

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

                long durationInSeconds = DurationCalculator
                    .calculateSecondsBetweenTwoPointsInTime(dateA, dateB);
                System.out
                    .println("The duration between " + dateA.format(FORMATTER)
                            + " and " + dateB.format(FORMATTER) + " is: "
                            + nline + DurationCalculator
                                .convertDuationToString(durationInSeconds));

                System.out.println("Do you require more calculations?" + nline
                        + "0 - Yes" + nline + "1 - No");
                do
                {
                    if (wrongInput)
                        System.out.println("Enter a valid command ...");
                    input = scanner.next();
                    wrongInput = true;
                }
                while (!(input.equals("0") || input.equals("1")
                        || input.toLowerCase()
                            .equals("quit")));

                if (!input.equals("0")) quit();
                wrongInput = false;
            }
            else if (input.equals("1"))
            {

            }
            else
            {
                quit();
            }
        }
        while (true);
    }

    private void sendWelcomeMessage()
    {
        System.out.println("Welcome to the duration calculator!");
    }

    private LocalDateTime enterDateDetails()
    {
        boolean wrongInput = false;
        int year, month, day, hour, minute, second;
        System.out.println("Enter the year of the date: ");
        do
        {
            if (wrongInput) System.out.println("Enter a valid command ...");
            input = scanner.next();
            wrongInput = true;
        }
        while (!((input.matches("\\d{4}") && Integer.parseInt(input) >= 0)
                || input.equals("quit")));
        if (input.equals("quit")) quit();
        year = Integer.parseInt(input);
        wrongInput = false;

        System.out.println("Enter the month of the date: ");
        do
        {
            if (wrongInput) System.out.println("Enter a valid command ...");
            input = scanner.next();
            wrongInput = true;
        }
        while (!(input.matches("\\d{1,2}") || input.equals("quit")));
        if (input.equals("quit")) quit();
        month = Integer.parseInt(input);
        wrongInput = false;

        System.out.println("Enter the day of the date: ");
        do
        {
            if (wrongInput) System.out.println("Enter a valid command ...");
            input = scanner.next();
            wrongInput = true;
        }
        while (!(input.matches("\\d{1,2}") || input.equals("quit")));
        if (input.equals("quit")) quit();
        day = Integer.parseInt(input);
        wrongInput = false;

        System.out.println("Enter the hour of the date: ");
        do
        {
            if (wrongInput) System.out.println("Enter a valid command ...");
            input = scanner.next();
            wrongInput = true;
        }
        while (!(input.matches("\\d{1,2}") || input.equals("quit")));
        if (input.equals("quit")) quit();
        hour = Integer.parseInt(input);
        wrongInput = false;

        System.out.println("Enter the minute of the date: ");
        do
        {
            if (wrongInput) System.out.println("Enter a valid command ...");
            input = scanner.next();
            wrongInput = true;
        }
        while (!(input.matches("\\d{1,2}") || input.equals("quit")));
        if (input.equals("quit")) quit();
        minute = Integer.parseInt(input);
        wrongInput = false;

        System.out.println("Enter the second of the date: ");
        do
        {
            if (wrongInput) System.out.println("Enter a valid command ...");
            input = scanner.next();
            wrongInput = true;
        }
        while (!(input.matches("\\d{1,2}") || input.equals("quit")));
        if (input.equals("quit")) quit();
        second = Integer.parseInt(input);
        wrongInput = false;

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    private void quit()
    {
        scanner.close();
        System.out.println("Bye!");
        System.exit(0);
    }

    public static void main(String[] args) throws Exception
    {
        Console console = new Console();
        console.startDialog();
    }
}
