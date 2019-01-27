package com.epam.preprod.karavayev.nonshop.apputils;

import com.epam.preprod.karavayev.nonshop.filefilters.FileFilter;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    private final static String DATE_FORMAT = "dd-MM-yyyy HH:mm";
    private final static String DATE_PATTERN = "[0-3][0-9]-[01][0-9]-([0-9]){4}\\s([0-9]){2}:([0-9]){2}";

    public final static String EXIT = "x";

    private ConsoleManager consoleManager;

    public AppUtils() {
        consoleManager = new ConsoleManager();
    }

    public boolean isExitInput(String input) {
        if (input.equalsIgnoreCase(EXIT)) {
            consoleManager.promptMessage("See you next time");
            return true;
        }
        return false;
    }

    public String checkDirectoryInput(String input) {
        String s = input;
        while (!isDirectory(s) && !s.equalsIgnoreCase(EXIT)) {
            consoleManager.promptMessage("Not a directory. Try a new directory path");
            s = consoleManager.takeInput();
        }
        return s;
    }

    public String checkSizeInput(String input) {
        String s = input;
        while (!isCorrectNumber(s)) {
            consoleManager.promptMessage("Wrong input. Try again");
            s = consoleManager.takeInput();
        }
        return s;
    }

    public String checkInput(String s1, String s2, String s3) {
        String input = s3;
        while (!isInputCorrect(s1, s2, input)) {
            consoleManager.promptMessage("Wrong input. Try again");
            input = consoleManager.takeInput();
        }
        return input;
    }

    public String checkInputDate(String inputDate) {
        String s = inputDate;
        while(!isDateStringCorrect(s)) {
            consoleManager.promptMessage("Wrong date format. Try again");
            s = consoleManager.takeInput();
        }
        return s;
    }

    private boolean isDirectory(String input) {
        return new File(input).isDirectory();
    }

    private boolean isInputCorrect(String s1, String s2, String s3) {
        return s3.equalsIgnoreCase(s1) || s3.equalsIgnoreCase(s2);
    }

    private boolean isCorrectNumber(String input) {
        return input.chars().allMatch(Character::isDigit);
    }

    public long convertToLong(String s) {
        return Long.parseLong(s);
    }

    public long convertStringDateToLong(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private boolean isDateStringCorrect(String dateString) {
        return dateString.matches(DATE_PATTERN);
    }

    public List<File> collectResults(List<File> filesOfDirectory, FileFilter fileFilter) {
        List<File> results = new ArrayList<>();
        for (File f: filesOfDirectory) {
            if (fileFilter.handleRequest(f)) {
                results.add(f);
            }
        }
        return results;
    }
}
