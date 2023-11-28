package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductStep}
 */
public record ProductStepDto(
        UserDto createdBy,
        Long id,
        Step step,
        LocalDateTime start,
        LocalDateTime finish,
        Duration duration,
        boolean finished,
        boolean paused
) implements Serializable {
}