package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.User;

public interface UserService extends BaseService<User,Long> {


    User login(String username, String password);

    void enableUser(long userId);

    void disableUser(long userId);

    boolean isUserAvailable(User user);
}
