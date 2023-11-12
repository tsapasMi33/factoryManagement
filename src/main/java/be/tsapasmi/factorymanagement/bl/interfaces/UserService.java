package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BaseService<User,Long>, UserDetailsService {
}
