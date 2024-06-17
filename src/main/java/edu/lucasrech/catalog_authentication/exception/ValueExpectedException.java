package edu.lucasrech.catalog_authentication.exception;

import lombok.Getter;

@Getter
public class ValueExpectedException extends Exception{
    private final String[] request;
    public ValueExpectedException(String[] request) {
        super("A value is expected. It cannot be null");
        this.request = request;
    }


}
