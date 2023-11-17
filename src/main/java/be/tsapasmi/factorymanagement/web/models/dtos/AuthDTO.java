package be.tsapasmi.factorymanagement.web.models.dtos;

import java.io.Serializable;


public record AuthDTO(String username, String token, String role) implements Serializable {
}
