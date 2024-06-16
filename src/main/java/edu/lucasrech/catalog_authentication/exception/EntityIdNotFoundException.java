package edu.lucasrech.catalog_authentication.exception;

public class EntityIdNotFoundException extends Exception {
    private final String request;
    public EntityIdNotFoundException(String message, String request) {
        super(message);
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
