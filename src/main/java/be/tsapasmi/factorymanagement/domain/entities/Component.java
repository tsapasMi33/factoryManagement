package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Component extends BaseEntity {

    private String name;

    private String type;

    @Enumerated(EnumType.STRING)
    private Material material;

    private Integer thickness;

    private Integer length;

    private Integer width;

    private double price;
}
