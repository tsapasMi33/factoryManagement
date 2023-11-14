package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.dal.BatchRepository;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class BatchServiceImpl extends BaseServiceImpl<Batch,Long, BatchRepository> implements BatchService {

    private final EntityManager entityManager;
    public BatchServiceImpl(BatchRepository repository, EntityManager entityManager) {
        super(repository, Batch.class);
        this.entityManager = entityManager;
    }

    @Override
    public Batch getLastBatch() {
        return repository.getLastBatch()
                .orElse(null);
    }

    @Override
    public List<Batch> findAllByCriteria(Step step) {
        Specification<Batch> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (step != null) {
                predicates.add(criteriaBuilder.equal(root.get("currentStep"), step));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(specification);
    }

    @Override
    public List<Batch> findAllToCut() {
        Specification<Batch> specification = ((root, query, criteriaBuilder) -> {
            Join<Batch,Product> productJoin = root.join("products");
            Join<Product, ProductVariant> variantJoin = productJoin.join("variant");

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.isTrue())
        })

        return null;
    }

    private Predicate isCurrentStepBefore(Step currentStep,
                                          Step targetStep,
                                          List<Step> productionPath) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        Expression<Integer> currentIndex = cb.indexOf(productionPath, currentStep);
    }

    @Override
    public Batch create(Batch entity) {
        Batch created = super.create(entity);
        repository.updateProducts(created, created.getProducts());
        return created;
    }
}
