package com.epam.preprod.karavayev.model.myexception;

public class UnmodifiableListException extends RuntimeException {
    public UnmodifiableListException(String message) {
        super(message);
    }
}
