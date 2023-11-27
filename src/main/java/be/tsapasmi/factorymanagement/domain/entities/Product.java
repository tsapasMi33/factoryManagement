package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product", indexes = {
        @Index(name = "idx_product_archived", columnList = "archived")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;

    private String code;

    @Enumerated(EnumType.STRING)
    private Step currentStep;

    @Enumerated(EnumType.STRING)
    private Step nextStep = Step.ENCODED;

    @ManyToOne
    private ProductVariant variant;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductStep> steps = new ArrayList<>(12);

    @ManyToOne
    private Batch batch;

    @ManyToOne
    private Packet packet;

    private boolean archived;
}
