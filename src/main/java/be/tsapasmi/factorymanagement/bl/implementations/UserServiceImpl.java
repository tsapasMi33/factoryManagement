package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.UserNotFoundException;
import be.tsapasmi.factorymanagement.bl.exceptions.UserStateException;
import be.tsapasmi.factorymanagement.bl.interfaces.UserService;
import be.tsapasmi.factorymanagement.dal.UserRepository;
import be.tsapasmi.factorymanagement.domain.entities.User;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService {


    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        super(repo, User.class);
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User login(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        auth = authenticationManager.authenticate(auth);
        return repository.findByUsername(auth.getName())
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void enableUser(long userId) {
        if (repository.existsByIdAndEnabledIs(userId, true)) {
            throw new UserStateException(true);
        }
        repository.toggleUser(userId, true);
    }

    @Override
    public void disableUser(long userId) {
        if (repository.existsByIdAndEnabledIs(userId, false)) {
            throw new UserStateException(false);
        }
        repository.toggleUser(userId, false);
    }

    @Override
    public boolean isUserAvailable(User user) {
        return repository.isUserAvailable(user);
    }

    @Override
    public User create(User entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return super.create(entity);
    }

    @Override
    public User update(Long id, User entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return super.update(id, entity);
    }


}
