package com.epam.preprod.karavayev.model.myexception;

public class WrongInstrumentTypeException extends RuntimeException {
    public WrongInstrumentTypeException(String message) {
        super(message);
    }
}
