package edu.lucasrech.catalog_authentication.exception;

public class ValueExpectedException extends Exception{
    public ValueExpectedException() {
        super("A value is expected. It cannot be null");
    }


}
