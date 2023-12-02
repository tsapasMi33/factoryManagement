package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.PricingMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialType extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Material material;

    @Enumerated(EnumType.STRING)
    private PricingMethod pricingMethod;

    private Double basePrice;

    private boolean requiresCutting;

    private boolean requiresBending;

    private boolean hasThickness;

    private boolean hasLength;

    private boolean hasWidth;
}
