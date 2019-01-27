package com.epam.preprod.karavayev.nonshop.task8.myexception;

public class WrongFileException extends RuntimeException {

    public WrongFileException(String message) {
        super(message);
    }
}
