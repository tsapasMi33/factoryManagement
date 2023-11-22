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
import jakarta.persistence.criteria.*;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class BatchServiceImpl extends BaseServiceImpl<Batch, Long, BatchRepository> implements BatchService {

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
    public Page<Batch> findAllByCriteria(int page, Step currentStep, Step nextStep) {
        Specification<Batch> specification = (root, query, criteriaBuilder) -> {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Product> productSubqueryRoot = subquery.from(Product.class);
            subquery.select(criteriaBuilder.literal(1L))
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(productSubqueryRoot.get("batch"), root),
                            criteriaBuilder.or(
                                    criteriaBuilder.equal(productSubqueryRoot.get("currentStep"), currentStep),
                                    criteriaBuilder.equal(productSubqueryRoot.get("nextStep"), nextStep)
                            )
                    ));

            return criteriaBuilder.exists(subquery);
        };

        return repository.findAll(specification, PageRequest.of(page - 1, 5));
    }

    @Override
    public Batch startStep(Step targetStep, Long batchId) {
        if (!targetStep.isBatchStep()) {
            throw new BatchInSingleProductStepException();
        }
        Batch batch = repository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException(batchId, Batch.class));

        batch.getProducts()
                .forEach(product -> productService.startStep(targetStep, product));
        return repository.save(batch);
    }

    @Override
    public void pauseStep(Step targetStep, Long batchId) {
        Batch batch = repository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException(batchId, Batch.class));

        batch.getProducts()
                .forEach(product -> productService.pauseStep(targetStep, product, batch.getProducts().size()));

        repository.save(batch);
    }

    @Override
    public void finishStep(Step targetStep, Long batchId) {
        Batch batch = repository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException(batchId, Batch.class));

        batch.getProducts()
                .forEach(product -> productService.finishStep(targetStep, product, batch.getProducts().size()));

        repository.save(batch);
    }

    @Override
    public Batch create(Batch entity) {
        entity.setProducts(
                entity.getProducts().stream()
                        .map(product -> productService.getOne(product.getId()))
                        .toList()
        );
        entity.setCode(generateCode());

        Client client = entity.getProducts().get(0).getOrder().getClient();
        if (entity.getProducts().stream().anyMatch(product -> product.getOrder().getClient() != client)) {
            throw new IllegalCollectionException("All Batch products must belong to the same client");
        }
        entity.getProducts()
                .forEach(product -> productService.startStep(Step.PRODUCTION, product));

        Batch created = super.create(entity);
        repository.updateProducts(created, created.getProducts());
        return created;
    }

    private String generateCode() {

        Batch lastBatch = repository.getLastBatch().orElse(null);
        LocalDate today = LocalDate.now();

        if (lastBatch != null && lastBatch.getCreatedDate().toLocalDate().isEqual(today)) {
            long code = Long.parseLong(lastBatch.getCode());
            return String.valueOf(++code);
        }

        return String.valueOf(today.getYear()).substring(2, 4) +
                (today.get(ChronoField.ALIGNED_WEEK_OF_YEAR) < 10 ? "0" + today.get(ChronoField.ALIGNED_WEEK_OF_YEAR) : today.get(ChronoField.ALIGNED_WEEK_OF_YEAR)) +
                today.getDayOfWeek().getValue() +
                "001";
    }
}
