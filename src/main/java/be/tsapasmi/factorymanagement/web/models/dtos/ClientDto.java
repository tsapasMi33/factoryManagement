package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.entities.Address;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Client}
 */
public record ClientDto(Long id, String name, String companyType, Address address,
                        int discountPercentage) implements Serializable {
}
