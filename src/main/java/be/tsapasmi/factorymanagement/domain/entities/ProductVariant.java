package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Material material;

    private String variantIdentifier;

    private Integer width;

    private Integer length;

    private Integer height;

    private String code;

    private double price;

    private String description;

    @ManyToOne
    private ProductFamily productFamily;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Component> components;
}
