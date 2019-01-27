package com.epam.preprod.karavayev.shop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputProcessor {

    public static final String datePattern1 = "dd-MM-yyyy HH-mm";
    public static final String datePattern2 = "dd-MM-yyyy";
    public static final String WRONG_INPUT = "Wrong input";
    public static final String WRONG_DATE_FORMAT = "Wrong date format";

    private final String EXIT = "x";

    private SimpleDateFormat format1;
    private SimpleDateFormat format2;


    public InputProcessor() {
        format1 = new SimpleDateFormat(datePattern1);
        format2 = new SimpleDateFormat(datePattern2);
    }

    public String checkInput(String input) {
        if (input.equalsIgnoreCase(EXIT)) {
            return input;
        }
        if (input.length() > 0 && isAllDigits(input)) {
            return input;
        }
        return WRONG_INPUT;
    }

    public String checkInput(String s, String yes, String no) {
        if (s.equalsIgnoreCase(no)) {
            return no;
        }
        if (s.equalsIgnoreCase(yes)) {
            return yes;
        }
        return WRONG_INPUT;
    }

    public String checkInput(String input, String option1, String option2, String exit) {

        if (input.equalsIgnoreCase(exit)) {
            return exit;
        }
        if(input.equalsIgnoreCase(option1)) {
            return option1;
        }
        if(input.equalsIgnoreCase(option2)) {
            return option2;
        }
        return WRONG_INPUT;
    }

    public String checkDateInput(String input) {
        if (validateDate(input)) {
            return input;
        }
        return WRONG_DATE_FORMAT;
    }

    public boolean isInputExit(String s) {
        return s.equalsIgnoreCase(EXIT);
    }

    public boolean isAllDigits(String string) {
        return string.chars().mapToObj(i-> (char)i).allMatch(Character::isDigit);
    }

    private boolean validateDate(String date) {
        if (date == null) return false;
        format1.setLenient(false);
        try {
            format1.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public Date convertStringToDate(String dateInString) throws ParseException {
        return (dateInString.length() <= 10) ?
                format2.parse(dateInString) : format1.parse(dateInString);

    }

    public String getStringFromDate(Date date) {
        return format1.format(date);
    }

    public int findTimeDifference(Date d1, Date d2) {
        int cof = 1000 * 60 * 60;
        return (int)Math.abs((d1.getTime() - d2.getTime()) / cof);
    }


}
