package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.User;

public interface AuthService {
    User login(String username, String password);
}
