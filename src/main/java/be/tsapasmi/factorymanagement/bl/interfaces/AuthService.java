package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.domain.enums.Role;

public interface AuthService {

    User login(String username, String password);

    User createUser(User user);
}
