package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.AuthService;
import be.tsapasmi.factorymanagement.dal.UserRepository;
import be.tsapasmi.factorymanagement.domain.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        auth = authenticationManager.authenticate(auth);

        return repository.findByUsername(auth.getName())
                .orElseThrow(); // TODO exception
    }
}
