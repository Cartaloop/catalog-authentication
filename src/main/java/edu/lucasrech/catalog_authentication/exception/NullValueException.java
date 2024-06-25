package edu.lucasrech.catalog_authentication.exception;

public class NullValueException extends Exception {

    public NullValueException() {
        super("The value entered cannot be null.");
    }
}
