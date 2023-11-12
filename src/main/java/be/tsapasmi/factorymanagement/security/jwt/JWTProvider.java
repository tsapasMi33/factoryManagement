package be.tsapasmi.factorymanagement.security.jwt;

import be.tsapasmi.factorymanagement.bl.interfaces.UserService;
import be.tsapasmi.factorymanagement.domain.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@AllArgsConstructor
public class JWTProvider {

    private final String secret = "qzwV$a5YBx#IJbGpCa^eVYrA^]u::;1#]|E~mYg0(+ZfOZ9GdK*Vc:->GYmJZzX?p_qU7wXv:;9O)!LQRd1ex]:iZ1B+xZfA^~#T*L&Q?8/<$O`]Ttzm6LpZ";

    private final UserService userService;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plus(10, ChronoUnit.HOURS))
                .sign(Algorithm.HMAC512(secret));
    }

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }

    public UserDetails isTokenValid(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret))
                .acceptExpiresAt(36_000_000)
                .build();

        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();

            return userService.loadUserByUsername(username);
        } catch (JWTVerificationException ex) {
            return null;
        }
    }

    public Authentication generateAuth(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}
