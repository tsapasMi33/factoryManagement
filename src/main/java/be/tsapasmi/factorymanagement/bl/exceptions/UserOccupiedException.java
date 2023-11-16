package be.tsapasmi.factorymanagement.bl.exceptions;


public class UserOccupiedException extends RuntimeException {
    public UserOccupiedException(String username) {
        super("User " + username + " is already working on another task!");
    }
}
