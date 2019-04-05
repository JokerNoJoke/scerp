package com.scerp.app.example.exception;

/**
 * App custom exception
 */
public final class AppException extends RuntimeException {

    /**
     * Error Identification
     */
    private String id;

    public AppException(String id, String message) {
        super(message);
        this.id = id;
    }

    @Override
    public String toString() {
        return "AppException{" +
                "id='" + id + '\'' +
                super.toString() +
                '}';
    }

}
