package com.github.skonline90.main;

import com.github.skonline90.exceptions.TimeException;
import com.github.skonline90.view.Console;

/**
 * This class starts the application.
 *
 * @author skonline90
 * @version 24.12.2018
 */
public class Startup
{
    public static void main(String[] args) throws TimeException
    {
        Console console = new Console();
        console.startDialog();
    }
}
