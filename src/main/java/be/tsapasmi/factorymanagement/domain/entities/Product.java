package be.tsapasmi.factorymanagement.domain.entities;

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
public class Product extends BaseEntity {

    private String comments;

    @Enumerated(EnumType.STRING)
    private Step currentStep;

    @ManyToOne
    private ProductVariant variant;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductStep> steps;
}
