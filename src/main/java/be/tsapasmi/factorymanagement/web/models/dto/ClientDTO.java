package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.entities.Address;
import be.tsapasmi.factorymanagement.domain.entities.Client;

import java.io.Serializable;

/**
 * DTO for {@link Client}
 */
public record ClientDTO(Long id, String name, String companyType, Address address,
                        int discountPercentage) implements Serializable {
}
