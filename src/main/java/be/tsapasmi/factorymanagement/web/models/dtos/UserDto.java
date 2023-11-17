package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.User}
 */
public record UserDto(LocalDateTime createdDate, Long id, String username, Role role, boolean enabled,
                      Double costPerMinute) implements Serializable {
}
