package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.entities.Address;

import java.io.Serializable;

public record ClientDTO(long id, String name, String companyType, Address address, int discountPercentage) implements Serializable {
}
