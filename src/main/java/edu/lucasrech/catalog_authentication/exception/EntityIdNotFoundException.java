package edu.lucasrech.catalog_authentication.exception;

public class EntityNotFoundException extends Exception {
    private final String request;
    public EntityNotFoundException(String message, String request) {
        super(message);
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
