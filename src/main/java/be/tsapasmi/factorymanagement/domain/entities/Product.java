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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;

    @Enumerated(EnumType.STRING)
    private Step currentStep;

    @Enumerated(EnumType.STRING)
    private Step nextStep;

    @ManyToOne
    private ProductVariant variant;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductStep> steps;

    @ManyToOne
    private Batch batch;

    @ManyToOne
    private Packet packet;

    {
        steps = new ArrayList<>();
        nextStep = Step.ENCODED;
    }

    public void doNextStep() {
        currentStep = nextStep;
        int nextStepIndex = variant.getProductionPath().indexOf(nextStep) + 1;
        if (nextStepIndex < variant.getProductionPath().size()) {
            nextStep = variant.getProductionPath().get(nextStepIndex);
        } else {
            nextStep = null;
        }
    }
}
