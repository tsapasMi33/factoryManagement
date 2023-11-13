package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.ResourceNotFoundException;
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
public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repo) {
        super(repo, User.class);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = repository.findById(1L).get();
        return repository.findByUsername(username)
                .orElse(user ); // TODO no user means exception
    }
}
