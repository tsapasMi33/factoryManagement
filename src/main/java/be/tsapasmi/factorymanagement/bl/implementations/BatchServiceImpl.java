package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.BatchInSingleProductStepException;
import be.tsapasmi.factorymanagement.bl.exceptions.IllegalCollectionException;
import be.tsapasmi.factorymanagement.bl.exceptions.ResourceNotFoundException;
import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.dal.BatchRepository;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class BatchServiceImpl extends BaseServiceImpl<Batch,Long, BatchRepository> implements BatchService {

    private final ProductService productService;

    public BatchServiceImpl(BatchRepository repository, ProductService productService) {
        super(repository, Batch.class);
        this.productService = productService;
    }

    @Override
    public Batch getLastBatch() {
        return repository.getLastBatch()
                .orElse(null);
    }

    @Override
    public List<Batch> findAllByCriteria(Step currentStep, Step nextStep) {
        Specification<Batch> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Batch, Product> productJoin = root.join("products", JoinType.INNER);

            if (currentStep != null) {
                predicates.add(criteriaBuilder.equal(productJoin.get("currentStep"), currentStep));
            }
            if (nextStep != null) {
                predicates.add(criteriaBuilder.equal(productJoin.get("nextStep"), nextStep));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(specification);
    }

    @Override
    public Batch startStep(Step targetStep, Long batchId) {
        if (!targetStep.isBatchStep()) {
            throw new BatchInSingleProductStepException();
        }
        Batch batch = repository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException(batchId, Batch.class));

        batch.getProducts()
                .forEach(product -> productService.startStep(targetStep,product));
        return repository.save(batch);
    }

    @Override
    public void pauseStep(Step targetStep, Long batchId) {
        Batch batch = repository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException(batchId, Batch.class));

        batch.getProducts()
                .forEach(product -> productService.pauseStep(targetStep,product, batch.getProducts().size()));

        repository.save(batch);
    }

    @Override
    public void finishStep(Step targetStep, Long batchId) {
        Batch batch = repository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException(batchId, Batch.class));

        batch.getProducts()
                .forEach(product -> productService.finishStep(targetStep,product, batch.getProducts().size()));

        repository.save(batch);
    }

    @Override
    public Batch create(Batch entity) {
        Client client = entity.getProducts().get(0).getOrder().getClient();
        if (entity.getProducts().stream().anyMatch(product -> product.getOrder().getClient() != client)){
           throw new IllegalCollectionException("All Batch products must belong to the same client");
        }
        entity.getProducts()
                .forEach(product -> productService.startStep(Step.PRODUCTION, product));

        Batch created = super.create(entity);
        repository.updateProducts(created, created.getProducts());
        return created;
    }
}
