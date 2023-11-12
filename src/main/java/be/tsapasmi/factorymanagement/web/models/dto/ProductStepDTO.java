package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link ProductStep}
 */
public record ProductStepDTO(Long id, Long productId, List<Step> productVariantProductionPath, Step step,
                             LocalDateTime start, LocalDateTime finish, Duration duration, boolean finished,
                             String userUsername) implements Serializable {
}
