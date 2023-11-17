package be.tsapasmi.factorymanagement.config;


import be.tsapasmi.factorymanagement.domain.entities.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;



public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    @NonNull
    public Optional<User> getCurrentAuditor() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(user);
    }
}
