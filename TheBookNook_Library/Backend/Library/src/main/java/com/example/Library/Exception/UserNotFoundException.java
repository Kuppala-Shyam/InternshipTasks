package com.example.Library.Exception;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {
    private String msg = "User with that id is not registered!";

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     * @param msg The detail message.
     */
    public UserNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
