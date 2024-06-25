package edu.lucasrech.catalog_authentication.exception;

public class InvalidValueException extends Exception {
    public InvalidValueException(String invalidValue) {
        super("Invalid value: " + invalidValue);
    }
}
