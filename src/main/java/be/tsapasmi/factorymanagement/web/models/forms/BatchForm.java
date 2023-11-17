package be.tsapasmi.factorymanagement.web.models.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Batch}
 */
public record BatchForm(@NotNull @Size(min = 1) List<ProductIdForm> products) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
     */
    public record ProductIdForm(@Positive Long id) implements Serializable {
    }
}
