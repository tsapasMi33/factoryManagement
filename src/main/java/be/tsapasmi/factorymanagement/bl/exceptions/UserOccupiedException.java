package be.tsapasmi.factorymanagement.bl.exceptions;

import be.tsapasmi.factorymanagement.domain.entities.User;

public class UserOccupiedException extends RuntimeException {
    public UserOccupiedException(String username) {
        super("User" + username + " is already working on another task!");
    }
}
