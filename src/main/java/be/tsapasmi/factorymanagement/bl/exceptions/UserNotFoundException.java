package be.tsapasmi.factorymanagement.bl.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User does not exist!");
    }
}
