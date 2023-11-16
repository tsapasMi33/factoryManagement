package be.tsapasmi.factorymanagement.bl.exceptions;

public class UserStateException extends RuntimeException {
    public UserStateException(boolean state) {
        super("User is already " + (state ? "enabled!" : "disabled!"));
    }
}
