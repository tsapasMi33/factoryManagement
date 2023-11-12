package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.Step;
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
public class ProductVariant extends BaseEntity {

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "product_variant_steps", joinColumns = @JoinColumn(name = "product_variant_id"))
    @OrderColumn(name = "step_order")
    private List<Step> productionPath;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Component> components;
}
