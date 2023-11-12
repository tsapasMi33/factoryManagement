package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.UserService;
import be.tsapasmi.factorymanagement.dal.UserRepository;
import be.tsapasmi.factorymanagement.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService {

    private final UserRepository repository;

    @Override
    public Class<User> getResourceClass() {
        return User.class;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
