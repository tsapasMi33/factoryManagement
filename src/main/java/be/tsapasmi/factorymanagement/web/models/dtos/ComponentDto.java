package be.tsapasmi.factorymanagement.web.models.dtos;



import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Component}
 */
public record ComponentDto(Long id, String name, Integer thickness,
                           Integer length, Integer width, double price,
                           boolean requiresCutting, boolean requiresBending, MaterialTypeDto type) implements Serializable {
}
