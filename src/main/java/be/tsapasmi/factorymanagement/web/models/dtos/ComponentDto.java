package be.tsapasmi.factorymanagement.web.models.dtos;


import be.tsapasmi.factorymanagement.domain.enums.Material;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Component}
 */
public record ComponentDto(Long id, String name, Integer thickness,
                           Integer length, Integer width, double price, String typeName, Material typeMaterial,
                           boolean typeRequiresCutting, boolean typeRequiresBending) implements Serializable {
}
